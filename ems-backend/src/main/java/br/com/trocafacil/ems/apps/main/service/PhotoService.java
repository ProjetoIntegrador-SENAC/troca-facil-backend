package br.com.trocafacil.ems.apps.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  java.util.Optional;

import br.com.trocafacil.ems.domain.model.photo.Photo;
import jakarta.transaction.Transactional;
import br.com.trocafacil.ems.apps.main.repository.PhotoRepository;

@Service
public class PhotoService {
    

    @Autowired
    private PhotoRepository photoRepository;

    @Transactional
    public void saveImage(Long id, String path, String group){

        Optional<Photo> photo = photoRepository.findByExternalIdAndAccountProduct(id, group);
        if (!photo.isEmpty()) return;

        var photoToSave = new Photo(null, path, group, id);
        photoRepository.save(photoToSave);

    }

    public String getPhotoPath (Long external_id, String group){
        Optional<Photo> photo = photoRepository.findByExternalIdAndAccountProduct(external_id, group);
        if (photo.isPresent()) return photo.get().getPhotoPath();
        return "no image";
    }

    @Transactional
    public Photo findByExternalIdAndAccountProduct(Long id, String group){
        return photoRepository.findByIdAndAccountProduct(id, group);
    }

}
