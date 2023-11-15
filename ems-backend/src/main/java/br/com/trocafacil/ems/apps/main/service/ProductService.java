package br.com.trocafacil.ems.apps.main.service;

import br.com.trocafacil.ems.apps.main.repository.ProductRepository;
import br.com.trocafacil.ems.domain.helpers.enums.ProductStatus;
import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.User;
import br.com.trocafacil.ems.domain.model.product.Product;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountService accountService;

    @Transactional
    public Product save(Product product){
        return productRepository.save(product);
    }

    @Transactional
    public void updateProductsToAvaliable(List<Product> productsToUpdate){
        log.info("Updating products to avaliable!!");
        productsToUpdate.forEach(x -> x.setStatus(ProductStatus.DISPONIVEL));
        productRepository.saveAll(productsToUpdate);
        log.info("Some products have been updated to avaliable");
    }

    @Transactional
    public void updateProductToAvaliable(Product productToUpdate){
        log.info("Updating product to avaliable!!");
        productToUpdate.setStatus(ProductStatus.DISPONIVEL);
        productRepository.save(productToUpdate);
        log.info("Some products have been updated to avaliable");
    }

    @Transactional
    public void updateProductToExchanged(Product product){
        log.info("Updating products to exchanged");
        product.setStatus(ProductStatus.TROCADO);
        productRepository.save(product);
    }

    @Transactional
    public List<Product> findAllByUser(User user){
        Account account = accountService.getAccountByUserId(user.getId());
        var products = productRepository.findAllByAccount_id(account.getId());
        System.out.println("products is null? " + products.isEmpty());
        System.out.println("products size: " + products.size());
        for (Product product : products) {
            System.out.println(product);
        }

        return products;
    }

    @Transactional
    public Product findById(Long id) {
        var optionalP = productRepository.findById(id);
        if (optionalP.isEmpty()){
            throw new EntityNotFoundException();
        }
        return optionalP.get();
    }

    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public List<Product> feed(User user, Integer pageNumber, Integer pageSize){
        Account account = accountService.getAccountByUserId(user.getId());
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> products = productRepository.feed(account.getId(), ProductStatus.DISPONIVEL, pageable);
        return products.stream().toList();
    }

    @Transactional
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

}
