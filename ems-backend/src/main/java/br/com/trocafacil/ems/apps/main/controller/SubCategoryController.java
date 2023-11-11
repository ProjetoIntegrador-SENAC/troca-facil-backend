package br.com.trocafacil.ems.apps.main.controller;

import br.com.trocafacil.ems.apps.main.repository.CategoryRepository;
import br.com.trocafacil.ems.apps.main.repository.SubCategoryRepository;
import br.com.trocafacil.ems.domain.model.product.Category;
import br.com.trocafacil.ems.domain.model.product.SubCategory;
import br.com.trocafacil.ems.domain.model.product.dto.SubCategoryDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("subcategory")
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
public class SubCategoryController {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/create")
    public ResponseEntity<SubCategory> create(@RequestBody @Valid SubCategoryDto subCategoryDto){

        Optional<Category> category = categoryRepository.findById(subCategoryDto.category_id());

        if (category.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        SubCategory subCategory = subCategoryDto.createSubCategory(category.get());

        return ResponseEntity.ok(subCategoryRepository.save(subCategory));
    }

}