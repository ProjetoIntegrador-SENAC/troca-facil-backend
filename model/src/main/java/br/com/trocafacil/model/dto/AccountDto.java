package br.com.trocafacil.model.dto;

import br.com.trocafacil.model.entity.User;

public record AccountDto(String name, String surname, User user, String document) {
}
