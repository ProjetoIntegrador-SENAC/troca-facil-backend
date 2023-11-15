package br.com.trocafacil.ems.apps.main.repository;

import br.com.trocafacil.ems.domain.helpers.enums.ProductStatus;
import br.com.trocafacil.ems.domain.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByAccount_id(Long id);

    Page<Product> findAll(Pageable pageable);

    @Query("SELECT o FROM product o WHERE o.id <> :id AND o.status = :status")
    Page<Product> feed(Long id, ProductStatus status , Pageable page);

}
