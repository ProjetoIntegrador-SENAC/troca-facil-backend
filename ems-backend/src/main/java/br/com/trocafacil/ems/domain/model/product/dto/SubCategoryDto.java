package br.com.trocafacil.ems.domain.model.product.dto;

import br.com.trocafacil.ems.domain.model.product.Category;
import br.com.trocafacil.ems.domain.model.product.SubCategory;
import jakarta.validation.constraints.NotNull;

public record SubCategoryDto (
        @NotNull
        String dsSubCategory,
        Long category_id
) {

    public SubCategory createSubCategory(Category category){
        SubCategory subCategory = new SubCategory();
        subCategory.setCategory(category);
        subCategory.setDsSubCategory(dsSubCategory);
        return subCategory;
    }

}
