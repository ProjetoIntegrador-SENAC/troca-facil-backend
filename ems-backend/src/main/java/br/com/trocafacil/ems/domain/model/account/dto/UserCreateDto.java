package br.com.trocafacil.ems.domain.model.account.dto;

import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.Address;
import br.com.trocafacil.ems.domain.model.account.Role;
import br.com.trocafacil.ems.domain.model.account.User;
import jakarta.validation.constraints.NotNull;
import org.aspectj.weaver.ast.Not;

import java.time.LocalDate;
import java.util.Set;

public record UserCreateDto (
        String login,
        String password,
        @NotNull String address,
        String complement,
        @NotNull Long number,
        @NotNull LocalDate birthday,
        @NotNull String cpf,
        @NotNull String district,
        @NotNull String name,
        @NotNull String username,
        @NotNull String zip,
        @NotNull String phone
) {

    public User createUser(Role role){
        User user = new User();
        user.setLogin(this.login);
        user.setPassword(this.password);
        user.setAuthorities(Set.of(role));
        return user;
    }

    public Address createAddress(){
        Address address = new Address();
        address.setDsAddress(this.address);
        address.setZip(zip);
        address.setComplement(complement);
        address.setDistrict(district);
        return address;
    }

    public Account createAccount(User user, Address address){
        Account account = new Account();
        account.setPhoneNumber(phone);
        account.setUsername(username);
        account.setDocument(cpf);
        account.setAddress(address);
        account.setUser(user);
        account.setFullName(name);
        return account;
    }

}


