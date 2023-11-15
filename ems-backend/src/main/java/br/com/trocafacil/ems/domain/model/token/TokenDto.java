package br.com.trocafacil.ems.domain.model.token;

import br.com.trocafacil.ems.domain.model.account.Role;

import java.util.ArrayList;
import java.util.Set;

public record TokenDto(
        String token,
        Set<Role> authorities

        ) {
}
