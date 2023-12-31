package br.com.trocafacil.ems.domain.model.account.response;

import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.Address;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@Setter
public class AccountPhotoResponse {

    String login;
    @NotNull Long id;
    @NotNull String address;
    String complement;
    @NotNull int number;
    LocalDate birthday;
    @NotNull String cpf;
    @NotNull String district;
    @NotNull String name;
    @NotNull String username;
    @NotNull String zip;
    @NotNull String phone;
    @NotNull String photoPath;

    public AccountPhotoResponse(Account account, String photoPath){
        Address address1 = account.getAddress();

        this.login = account.getUser().getLogin();
        this.id = account.getId();
        this.address = address1.getDsAddress();
        this.complement = address1.getComplement();
        this.number = address1.getNumber();
//        this.birthday = account.getBirth();
        this.cpf = account.getDocument();
        this.district = address1.getDistrict();
        this.name = account.getFullName();
        this.username = account.getUsername();
        this.zip = address1.getZip();
        this.phone = account.getPhoneNumber();
        this.photoPath = photoPath;
    }

}
