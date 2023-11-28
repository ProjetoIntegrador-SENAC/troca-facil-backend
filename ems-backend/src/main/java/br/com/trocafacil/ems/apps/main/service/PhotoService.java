package br.com.trocafacil.ems.apps.main.service;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  java.util.Optional;

import br.com.trocafacil.ems.domain.model.photo.Photo;
import jakarta.transaction.Transactional;
import br.com.trocafacil.ems.apps.main.repository.PhotoRepository;

@Service
public class PhotoService {

    @Autowired
    private MyBlobService myBlobService;

    @Autowired
    private PhotoRepository photoRepository;

    @Transactional
    public void saveImage(Long id, String path, String group){

        Optional<Photo> photo = photoRepository.findByExternalIdAndAccountProduct(id, group);
        if (photo.isEmpty()) {
            var photoToSave = new Photo(null, path, group, id);
            photoRepository.save(photoToSave);

        } else{
            photo.get().setPhotoPath(path);
            photoRepository.save(photo.get());
        }
    }

    public String deleteImage(Long id, String accountProduct){
        Optional<Photo> photo = photoRepository.findByExternalIdAndAccountProduct(id, accountProduct);
        if (photo.isPresent()){
            String filename = photo.get().getPhotoPath().replace(myBlobService.getBasePath(), "");
            myBlobService.deleteFile(filename);
            photoRepository.delete(photo.get());
        } else {
            return "Failed to delete image";
        }
        return "Image deleted";
    }

    public String getPhotoPath (Long external_id, String group){
        Optional<Photo> photo = photoRepository.findByExternalIdAndAccountProduct(external_id, group);
        if (photo.isPresent()) return photo.get().getPhotoPath();
        return "no image";
    }

    @Transactional
    public Optional<Photo> findByExternalIdAndAccountProduct(Long id, String group){
        return photoRepository.findByExternalIdAndAccountProduct(id, group);
    }

}
