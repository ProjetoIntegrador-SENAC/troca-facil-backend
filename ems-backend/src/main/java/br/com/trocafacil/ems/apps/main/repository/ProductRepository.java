package br.com.trocafacil.ems.apps.main.repository;

import br.com.trocafacil.ems.domain.helpers.enums.ProductStatus;
import br.com.trocafacil.ems.domain.model.product.Product;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByAccount_id(Long id);

    Page<Product> findAll(Pageable pageable);

    @Query("SELECT o FROM product o WHERE o.id <> :id AND o.status = :status")
    Page<Product> feed(@Param("id") Long id, @Param("status") ProductStatus status , Pageable page);

    Page<Product> findByAccountIdNotAndStatus(Long id, ProductStatus status, Pageable pageable);

    Long countDistinctById(Long id);

    Long countDistinctByStatus(ProductStatus status);

}
