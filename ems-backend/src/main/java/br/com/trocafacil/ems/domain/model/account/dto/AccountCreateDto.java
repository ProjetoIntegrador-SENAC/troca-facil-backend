package br.com.trocafacil.ems.domain.model.account.dto;

import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.Address;
import br.com.trocafacil.ems.domain.model.account.User;
import jakarta.validation.constraints.NotNull;

public record AccountCreateDto (
        @NotNull String fullName,
        @NotNull String username,
        @NotNull String document,
        @NotNull Address address,

        String phoneNumber



        ){
    public Account createAccount(User user){
        Account account = new Account();
        account.setUser(user);
        account.setAddress(this.address);
        account.setDocument(this.document);
        account.setFullName(this.fullName);
        account.setUsername(this.username);
        account.setPhoneNumber(this.phoneNumber);
        return account;
    }
}
