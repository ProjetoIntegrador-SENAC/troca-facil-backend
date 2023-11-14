package br.com.trocafacil.ems.apps.main.service;

import br.com.trocafacil.ems.apps.main.repository.SubCategoryRepository;
import br.com.trocafacil.ems.domain.model.product.SubCategory;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Transactional
    public SubCategory findById(Long subCategoryId) {
        var optionalS = subCategoryRepository.findById(subCategoryId);
        if (optionalS.isEmpty()){
            throw new EntityNotFoundException();
        }
        return optionalS.get();
    }

    @Transactional
    public SubCategory save(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }
}
