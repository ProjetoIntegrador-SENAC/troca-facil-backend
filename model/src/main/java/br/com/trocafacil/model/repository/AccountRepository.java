package br.com.trocafacil.model.repository;

import br.com.trocafacil.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

    Account findByDocument(String document);
}
