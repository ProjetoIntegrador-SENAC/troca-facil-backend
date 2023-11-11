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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public record ProductCreateDto (
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
        product.setStatus(ProductStatus.FECHADO);
        product.setAmount(this.amount);
        product.setSubCategory(subCategory);
        product.setCategory(subCategory.getCategory());
        product.setName(this.name);
        return product;
    }

}
