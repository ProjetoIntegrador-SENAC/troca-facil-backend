package br.com.trocafacil.ems.domain.repository;

import br.com.trocafacil.ems.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>{
}
