package br.com.trocafacil.authentication.model.repository;

import br.com.trocafacil.authentication.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginRepository extends JpaRepository<User, String> {
    User findByLogin(String login);
}
