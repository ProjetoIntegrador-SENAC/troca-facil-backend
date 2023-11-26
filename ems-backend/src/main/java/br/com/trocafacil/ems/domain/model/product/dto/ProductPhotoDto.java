package br.com.trocafacil.ems.domain.model.product.dto;

import br.com.trocafacil.ems.domain.helpers.enums.ProductCondition;
import br.com.trocafacil.ems.domain.helpers.enums.ProductStatus;
import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.product.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Getter
@Setter
public class ProductPhotoDto {

    // f dono produto, preco produto, nome, f produto, username, preco, quantidade
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer amount;
    private LocalDate date;
    private ProductCondition curCondition;
    private ProductStatus status;
    private String category;
    private String subCategory;
    private String pathPhotoProductOwner;
    private String pathPhotoProduct;
    private String username;


    public ProductPhotoDto(Product product, String photoProduct, String photoProductOwner){

        Account account = product.getAccount();
        String category = product.getCategory().getDsCategory();
        String subCategory = product.getCategory().getDsCategory();
        String pathPhotoProduct = photoProduct;
        String pathPhotoProductOwner = photoProductOwner;

        this.name = product.getName();
        this.price = product.getPrice();
        this.amount = product.getAmount();
        this.date = product.getDate();
        this.curCondition = product.getCurCondition();
        this.status = product.getStatus();
        this.category = category;
        this.subCategory = subCategory;
        this.pathPhotoProduct = pathPhotoProduct;
        this.pathPhotoProductOwner = pathPhotoProductOwner;
        this.username = account.getUsername();
        this.id = product.getId();

    }

}



