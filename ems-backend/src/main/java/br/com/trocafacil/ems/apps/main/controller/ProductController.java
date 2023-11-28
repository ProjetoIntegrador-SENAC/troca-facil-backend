package br.com.trocafacil.ems.apps.main.controller;

import br.com.trocafacil.ems.apps.main.service.AccountService;
import br.com.trocafacil.ems.apps.main.service.MyBlobService;
import br.com.trocafacil.ems.apps.main.service.ProductService;
import br.com.trocafacil.ems.apps.main.service.SubCategoryService;
import br.com.trocafacil.ems.domain.helpers.enums.ProductStatus;
import br.com.trocafacil.ems.domain.helpers.enums.Status;
import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.User;
import br.com.trocafacil.ems.domain.model.product.Product;
import br.com.trocafacil.ems.domain.model.product.SubCategory;
import br.com.trocafacil.ems.domain.model.product.dto.ProductCreateDto;
import br.com.trocafacil.ems.domain.model.product.dto.ProductPersonalDto;
import br.com.trocafacil.ems.domain.model.product.dto.ProductPhotoDto;
import com.azure.spring.cloud.autoconfigure.implementation.jms.properties.AzureServiceBusJmsProperties;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("product")
@Slf4j
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
public class ProductController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MyBlobService myBlobService;

    @PostMapping("/create")
    public ResponseEntity<Product> create(@Validated @RequestBody ProductCreateDto productDto, @AuthenticationPrincipal User user){
        Account account = accountService.getAccountByUserId(user.getId());
        SubCategory subCategory = subCategoryService.findById(productDto.subCategoryId());
        Product product = productDto.createProduct(account, subCategory);

        return ResponseEntity.ok(productService.save(product));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Product> find(@PathVariable Long id){
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/findall")
    public  ResponseEntity<List<Product>> findall(){
        List<Product>  products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("findall/{username}")
    public ResponseEntity<List<ProductPersonalDto>> findAllPersonal(@PathVariable(name = "username") String username){
        return ResponseEntity.ok().body(productService.findAllByUser(username));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        this.productService.deleteById(id);
    }

    @PostMapping("/update")
    public ResponseEntity<Product> update(@RequestBody @Valid Product product){
        Product product1 = this.productService.save(product);
        return ResponseEntity.ok(product1);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) throws IOException {
        log.info("Filename: " + file.getOriginalFilename());
        log.info("Size:" + file.getSize());
        log.info("Content-type: " + file.getContentType());
        var idLong = Long.parseLong(id);
        myBlobService.storeProductFile(file.getOriginalFilename(),file.getInputStream(), file.getSize(), idLong);
        var response =  file.getOriginalFilename() + " Has been saved as a blob-item!!!";
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/feed/{pageNumber}/{pageSize}")
    public ResponseEntity<List<ProductPhotoDto>> feed(@AuthenticationPrincipal User user,
                                              @PathVariable(required = false, name = "pageNumber") Integer pageNumber,
                                              @PathVariable(required = false, name = "pageSize") Integer pageSize){

        List<ProductPhotoDto> products = productService.feed(user, pageNumber, pageSize);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/amount/total")
    public Long getTotalAmountProduct(){
        return productService.getTotalAmountProduct();
    }

    @GetMapping("/amount/{status}")
    public Long getAmountProductByStatus(@PathVariable(required = false, name = "status")
                                         ProductStatus status){
        return productService.getTotalAmountProductByStatus(status);
    }



}
