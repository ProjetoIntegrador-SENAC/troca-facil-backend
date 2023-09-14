package br.com.trocafacil.model.repository;

import br.com.trocafacil.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
