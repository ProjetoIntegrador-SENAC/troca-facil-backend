package br.com.trocafacil.ems.apps.trade.repository;

import br.com.trocafacil.ems.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>{
}
