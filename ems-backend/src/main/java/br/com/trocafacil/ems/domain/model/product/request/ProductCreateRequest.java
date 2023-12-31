package br.com.trocafacil.ems.domain.model.product.request;

import br.com.trocafacil.ems.domain.helpers.enums.ProductCondition;
import br.com.trocafacil.ems.domain.helpers.enums.ProductStatus;
import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.product.Product;
import br.com.trocafacil.ems.domain.model.product.SubCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductCreateRequest(

        Long id,
        @NotNull String name,
        @NotNull Double price,
        @NotNull
        Integer amount,

        @NotNull
        @Enumerated(EnumType.STRING)
        ProductCondition curCondition,

        @NotNull
        Long subCategoryId

        ){

    public Product createProduct(Account account, SubCategory subCategory){
        Product product = new Product();
        product.setAccount(account);
        product.setDate(LocalDate.now());
        product.setCurCondition(this.curCondition);
        product.setPrice(BigDecimal.valueOf(this.price));
        product.setStatus(ProductStatus.DISPONIVEL);
        product.setAmount(this.amount);
        product.setSubCategory(subCategory);
        product.setCategory(subCategory.getCategory());
        product.setName(this.name);

        if(!(this.id == null)){
            product.setId(this.id);
        }

        return product;
    }

}
