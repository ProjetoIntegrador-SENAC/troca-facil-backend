package br.com.trocafacil.ems.domain.model.account.dto;

import br.com.trocafacil.ems.domain.model.account.Role;
import br.com.trocafacil.ems.domain.model.account.User;

import java.util.Set;

public record UserCreateDto (
        String login,
        String password
) {

    public User createUser(Role role){
        User user = new User();
        user.setLogin(this.login);
        user.setPassword(this.password);
        user.setAuthorities(Set.of(role));
        return user;
    }

}
