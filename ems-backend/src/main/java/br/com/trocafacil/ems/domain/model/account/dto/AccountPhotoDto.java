package br.com.trocafacil.ems.domain.model.account.dto;

import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.Address;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class AccountPhotoDto {

    String login;
    @NotNull Long id;
    @NotNull String address;
    String complement;
    @NotNull int number;
    @NotNull LocalDate birthday;
    @NotNull String cpf;
    @NotNull String district;
    @NotNull String name;
    @NotNull String username;
    @NotNull String zip;
    @NotNull String phone;
    @NotNull String photoPath;

    public AccountPhotoDto(Account account,String photoPath){
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
