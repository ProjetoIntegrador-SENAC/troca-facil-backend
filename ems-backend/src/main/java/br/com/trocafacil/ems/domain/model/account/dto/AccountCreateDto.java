package br.com.trocafacil.ems.domain.model.account.dto;

import jakarta.validation.constraints.NotNull;

public record AccountCreateDto (
        @NotNull String name
){}
