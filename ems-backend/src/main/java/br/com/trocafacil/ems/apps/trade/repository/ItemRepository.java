package br.com.trocafacil.ems.apps.trade.repository;

import br.com.trocafacil.ems.domain.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Product, Long>{
}
