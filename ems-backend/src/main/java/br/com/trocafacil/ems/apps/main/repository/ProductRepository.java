package br.com.trocafacil.ems.apps.main.repository;

import br.com.trocafacil.ems.domain.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
