package br.com.trocafacil.ems.apps.auth.repository;

import br.com.trocafacil.ems.domain.model.account.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
