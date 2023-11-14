package br.com.trocafacil.ems.apps.main.service;

import br.com.trocafacil.ems.apps.main.repository.CategoryRepository;
import br.com.trocafacil.ems.domain.model.product.Category;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Category save(Category category){
        return categoryRepository.save(category);
    }

    @Transactional
    public Category findById(Long categoryId) {
        var optional = categoryRepository.findById(categoryId);
        if (optional.isEmpty()){
            throw new EntityNotFoundException();
        }
        return optional.get();
    }
}
