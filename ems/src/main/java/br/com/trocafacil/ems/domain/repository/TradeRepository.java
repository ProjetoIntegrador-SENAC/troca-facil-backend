package br.com.trocafacil.ems.domain.repository;

import br.com.trocafacil.ems.domain.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
