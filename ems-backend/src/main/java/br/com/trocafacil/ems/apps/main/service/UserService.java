package br.com.trocafacil.ems.apps.main.service;

import br.com.trocafacil.ems.apps.main.repository.RoleRepository;
import br.com.trocafacil.ems.apps.main.repository.UserRepository;
import br.com.trocafacil.ems.domain.model.account.Role;
import br.com.trocafacil.ems.domain.model.account.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private RoleRepository authorityRepository;

    @Transactional
    public void adicionarRoleAoUsuario(User user, String role) {
//        user.getAuthorities().add();
    }
}
