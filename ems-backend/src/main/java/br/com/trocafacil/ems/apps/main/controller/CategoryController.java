package br.com.trocafacil.ems.apps.main.controller;

import br.com.trocafacil.ems.apps.main.service.CategoryService;
import br.com.trocafacil.ems.domain.model.product.Category;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/find")
    public ResponseEntity<List<Category>> findAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
