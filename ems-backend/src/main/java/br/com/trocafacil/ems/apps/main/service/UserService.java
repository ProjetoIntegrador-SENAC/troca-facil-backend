package br.com.trocafacil.ems.apps.main.service;

import br.com.trocafacil.ems.apps.main.repository.RoleRepository;
import br.com.trocafacil.ems.apps.main.repository.UserRepository;
import br.com.trocafacil.ems.domain.model.account.Role;
import br.com.trocafacil.ems.domain.model.account.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private RoleRepository authorityRepository;

    @Transactional
    public void adicionarRoleAoUsuario(String username, String roleName) {
        User usuario = usuarioRepository.findByLogin(username);
        Role authority = authorityRepository.findByName(roleName);
        if (usuario != null && authority != null) {
            Set<Role> authorities = usuario.getAuthorities();
            authorities.add(authority);
            usuario.setAuthorities(authorities);
            usuarioRepository.save(usuario);
        }
    }
}
