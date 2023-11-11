package br.com.trocafacil.ems.apps.main.repository;

import br.com.trocafacil.ems.domain.model.product.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    SubCategory findByDsSubCategory(String dsSubCategory);
}
