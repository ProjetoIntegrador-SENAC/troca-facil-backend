package br.com.trocafacil.ems.domain.model.account.dto;

import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.Address;
import br.com.trocafacil.ems.domain.model.account.User;
import jakarta.validation.constraints.NotNull;

public record AccountCreateDto (
        @NotNull String name,
        @NotNull String surname,
        @NotNull String document,
        @NotNull Address address

        ){
    public Account createAccount(User user){
        Account account = new Account();
        account.setUser(user);
        account.setAddress(this.address);
        account.setDocument(this.document);
        account.setName(this.name);
        account.setSurname(this.surname);
        return account;
    }
}
