package br.com.trocafacil.ems.apps.main.controller;

import br.com.trocafacil.ems.apps.main.repository.CategoryRepository;
import br.com.trocafacil.ems.apps.main.service.CategoryService;
import br.com.trocafacil.ems.domain.model.product.Category;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Category> create(@RequestBody @Valid Category category){
        return ResponseEntity.ok(categoryService.save(category));
    }

}
