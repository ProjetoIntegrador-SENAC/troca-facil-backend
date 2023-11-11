package br.com.trocafacil.ems.apps.main.repository;

import br.com.trocafacil.ems.domain.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
