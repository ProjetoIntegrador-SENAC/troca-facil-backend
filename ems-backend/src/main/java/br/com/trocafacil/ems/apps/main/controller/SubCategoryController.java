package br.com.trocafacil.ems.apps.main.controller;

import br.com.trocafacil.ems.apps.main.service.CategoryService;
import br.com.trocafacil.ems.apps.main.service.SubCategoryService;
import br.com.trocafacil.ems.domain.model.product.Category;
import br.com.trocafacil.ems.domain.model.product.SubCategory;
import br.com.trocafacil.ems.domain.model.product.dto.SubCategoryDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subcategory")
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<SubCategory> create(@RequestBody @Valid SubCategoryDto subCategoryDto){

        Category category = categoryService.findById(subCategoryDto.category_id());

        SubCategory subCategory = subCategoryDto.createSubCategory(category);
        return ResponseEntity.ok(subCategoryService.save(subCategory));
    }

    @GetMapping("/findall/{id}")
    public ResponseEntity<List<SubCategory>> findall(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(subCategoryService.findAll(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete (@PathVariable(name = "id") Long id){
        subCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}