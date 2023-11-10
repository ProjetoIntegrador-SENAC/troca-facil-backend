package br.com.trocafacil.ems.apps.main.controller;

import br.com.trocafacil.ems.apps.main.repository.ProductRepository;
import br.com.trocafacil.ems.domain.model.product.Product;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping
@RestController("product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @PostMapping("/create")
    public ResponseEntity create(@Validated @RequestBody Product product){
        Product product1 = productRepository.save(product);
        return ResponseEntity.ok(product1);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Product> find(@PathVariable Long id){
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()){
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok(product.get());
    }

    @GetMapping("/findall")
    public  ResponseEntity<List<Product>> findall(){
        List<Product>  products = productRepository.findAll();
        return ResponseEntity.ok(products);

    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        this.productRepository.deleteById(id);
    }

    @PostMapping("/update")
    public ResponseEntity<Product> update(@RequestBody @Valid Product product){
        Product product1;
        try {
            product1 = this.productRepository.save(product);
        }catch (Exception exception){
            throw new DataIntegrityViolationException("Erro ao atualizar produto");
        }

        return ResponseEntity.ok(product1);
    }

}
