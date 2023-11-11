package br.com.trocafacil.ems.apps.main.service;

import br.com.trocafacil.ems.apps.main.repository.ProductRepository;
import br.com.trocafacil.ems.domain.helpers.enums.ProductStatus;
import br.com.trocafacil.ems.domain.model.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public void updateProductsToCancelled(List<Product> productsToUpdate){
        log.info("Updating products to avaliable!!");
        productsToUpdate.forEach(x -> x.setStatus(ProductStatus.DISPONIVEL));
        productRepository.saveAll(productsToUpdate);
        log.info("Some products have been updated to avaliable");
    }

    public void updateProductToExchanged(Product product){
        log.info("Updating products to exchanged");
        product.setStatus(ProductStatus.TROCADO);
        productRepository.save(product);
    }

}
