package br.com.trocafacil.ems.apps.main.repository;

import br.com.trocafacil.ems.domain.model.account.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
