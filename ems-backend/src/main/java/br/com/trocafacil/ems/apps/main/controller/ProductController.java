package br.com.trocafacil.ems.apps.main.controller;

import br.com.trocafacil.ems.apps.main.repository.ProductRepository;
import br.com.trocafacil.ems.domain.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RequestMapping
@RestController("product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @PostMapping("/create")
    public ResponseEntity create(@Validated @RequestBody Product product){
        return ResponseEntity.ok(new Product());
    }

    @GetMapping("/find")
    public ResponseEntity<ArrayList<Product>> findAll(@RequestParam int intitial_number, @RequestParam int final_number){
        ArrayList<Product> products = new ArrayList<>();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        this.productRepository.deleteById(id);
    }
}
