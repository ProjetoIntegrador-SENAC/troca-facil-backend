package br.com.trocafacil.ems.apps.main.repository;

import br.com.trocafacil.ems.domain.model.product.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    SubCategory findByDsSubCategory(String dsSubCategory);
    List<SubCategory> findAllByCategoryId(Long id);
}
