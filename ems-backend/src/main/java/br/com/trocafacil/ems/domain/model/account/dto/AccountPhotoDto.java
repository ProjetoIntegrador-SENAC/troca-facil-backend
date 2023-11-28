package br.com.trocafacil.ems.domain.model.account.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class AccountPhotoDto {


    String login;
    @NotNull Long id;
    String password;
    @NotNull String address;
    String complement;
    @NotNull Long number;
    @NotNull LocalDate birthday;
    @NotNull String cpf;
    @NotNull String district;
    @NotNull String name;
    @NotNull String username;
    @NotNull String zip;
    @NotNull String phone;


}
