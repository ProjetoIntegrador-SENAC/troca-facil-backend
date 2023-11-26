package br.com.trocafacil.ems.apps.main.service;

import br.com.trocafacil.ems.apps.main.repository.ProductRepository;
import br.com.trocafacil.ems.domain.helpers.enums.ProductStatus;
import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.User;
import br.com.trocafacil.ems.domain.model.photo.Photo;
import br.com.trocafacil.ems.domain.model.photo.enums.PhotoEnum;
import br.com.trocafacil.ems.domain.model.product.Product;
import br.com.trocafacil.ems.domain.model.product.dto.ProductPersonalDto;
import br.com.trocafacil.ems.domain.model.product.dto.ProductPhotoDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PhotoService photoService;

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
    public List<ProductPersonalDto> findAllByUser(User user){
        Account account = accountService.getAccountByUserId(user.getId());
        List<Product> products = productRepository.findAllByAccount_id(account.getId());
        List<ProductPersonalDto> productPersonalDtos = new ArrayList<>();

        for (Product product : products) {
            String photopath = photoService.getPhotoPath(product.getId(), PhotoEnum.PRODUCT.name());
            ProductPersonalDto obj = new ProductPersonalDto(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getAmount(),
                    product.getCurCondition(),
                    product.getStatus(),
                    product.getCategory(),
                    product.getSubCategory(),
                    photopath
            );
            productPersonalDtos.add(obj);
        }

        return productPersonalDtos;
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
    public List<ProductPhotoDto> feed(User user, Integer pageNumber, Integer pageSize){
        Account account = accountService.getAccountByUserId(user.getId());
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> productsPage = productRepository.findByAccountIdNotAndStatus(account.getId(), ProductStatus.DISPONIVEL, pageable);
        List<Product> products = productsPage.stream().toList();

        List<ProductPhotoDto> dataResponse = new ArrayList<>();

        for (Product product: products){
            Account account1 = product.getAccount();

            String pathphotoAccount = "";
            String pathphotoProduct = "";

            Photo photoAccount = photoService.findByIdAndAccountProduct(account1.getId(), "ACCOUNT");
            Photo photoProduct = photoService.findByIdAndAccountProduct(product.getId(), "PRODUCT");

            if (!(photoAccount == null)){
                pathphotoAccount = photoAccount.getPhotoPath();
            }

            if (!(photoProduct == null)){
                pathphotoProduct = photoProduct.getPhotoPath();
            }

            dataResponse.add(new ProductPhotoDto(product, pathphotoAccount, pathphotoProduct));

        }

        return dataResponse;
    }

    @Transactional
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public String saveImage(Long id, String imagePath){
        photoService.saveImage(id, imagePath, "PRODUCT");
        return "Profile image saved with success!";
    }

}
