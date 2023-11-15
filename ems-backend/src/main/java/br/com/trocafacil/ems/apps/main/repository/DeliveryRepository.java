package br.com.trocafacil.ems.apps.main.repository;

import br.com.trocafacil.ems.domain.model.trade.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {}
