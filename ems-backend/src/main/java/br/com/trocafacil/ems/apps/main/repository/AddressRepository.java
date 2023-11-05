package br.com.trocafacil.ems.apps.main.repository;

import br.com.trocafacil.ems.domain.model.account.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
