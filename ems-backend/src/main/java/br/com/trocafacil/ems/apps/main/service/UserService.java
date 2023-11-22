package br.com.trocafacil.ems.apps.main.service;

import br.com.trocafacil.ems.apps.main.repository.RoleRepository;
import br.com.trocafacil.ems.apps.main.repository.UserRepository;
import br.com.trocafacil.ems.domain.model.account.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    public boolean saveEntity(User user){
        return verifyPassword(user);
    }

    private boolean verifyEmail(User user) {
        var email = user.getLogin();

        boolean emailContainsArroba = email.contains("@");
        boolean emailContainsEnd = email.contains(".com");
        String emailSplit[] = email.split("@");
        boolean emailLengthGreaterThan4 = emailSplit[0].length() > 4;

        return emailContainsArroba && emailContainsEnd && emailLengthGreaterThan4;
    }

    private boolean verifyPassword(User user){
        String password = user.getPassword();

        if (password.length() < 12){
            return false;
        }
        if (!password.contains("@") && !password.contains("!") && !password.contains("?") && !password.contains("#")){
            return false;
        }

        return true;
    }
}
