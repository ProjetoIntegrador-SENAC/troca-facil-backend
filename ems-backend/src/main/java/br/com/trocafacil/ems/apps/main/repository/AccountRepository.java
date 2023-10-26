package br.com.trocafacil.ems.apps.main.repository;

import br.com.trocafacil.ems.domain.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
