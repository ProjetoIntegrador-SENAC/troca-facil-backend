package br.com.trocafacil.ems.apps.main.controller;

import br.com.trocafacil.ems.apps.main.repository.AccountRepository;
import br.com.trocafacil.ems.apps.main.repository.ProductRepository;
import br.com.trocafacil.ems.apps.main.repository.SubCategoryRepository;
import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.User;
import br.com.trocafacil.ems.domain.model.product.Product;
import br.com.trocafacil.ems.domain.model.product.SubCategory;
import br.com.trocafacil.ems.domain.model.product.dto.ProductCreateDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
@Slf4j
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
public class ProductController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ProductRepository productRepository;
    @PostMapping("/create")
    public ResponseEntity<Product> create(@Validated @RequestBody ProductCreateDto productDto, @AuthenticationPrincipal User user){
        Account account = accountRepository.findByUserId(user.getId());
        SubCategory subCategory = subCategoryRepository.findById(productDto.subCategoryId()).orElse(null);
        Product product = productDto.createProduct(account, subCategory);
        return ResponseEntity.ok(productRepository.save(product));
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
