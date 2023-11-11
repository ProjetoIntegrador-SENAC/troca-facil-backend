package br.com.trocafacil.ems.domain.model.product.dto;

import br.com.trocafacil.ems.domain.helpers.enums.ProductCondition;
import br.com.trocafacil.ems.domain.helpers.enums.ProductStatus;
import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.product.Product;
import br.com.trocafacil.ems.domain.model.product.SubCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;

public record ProductCreateDto (
        @NotNull String name,
        @NotNull BigDecimal price,
        @Size(min = 1, max = 50)
        @NotNull
        int amount,

        @Temporal(TemporalType.DATE)
        Date date,

        @NotNull
        @Enumerated(EnumType.STRING)
        ProductCondition curCondition,

        @NotNull
        String subCategory

        ){

    public Product createProduct(Account account, SubCategory subCategory){
        Product product = new Product();
        product.setAccount(account);
        product.setDate(this.date);
        product.setCurCondition(this.curCondition);
        product.setPrice(this.price);
        product.setStatus(ProductStatus.FECHADO);
        product.setAmount(this.amount);
        product.setSubCategory(subCategory);
        product.setCategory(subCategory.getCategory());
        product.setName(this.name);
        return product;
    }

}
