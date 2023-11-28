package br.com.trocafacil.ems.apps.main.repository;

import br.com.trocafacil.ems.domain.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUserId(Long id);

    Account findByUsernameOrDocument(String cpj, String document);

    Optional<Account> findByUsername(String username);

}
