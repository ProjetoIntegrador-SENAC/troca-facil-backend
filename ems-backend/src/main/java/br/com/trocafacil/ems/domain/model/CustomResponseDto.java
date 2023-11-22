package br.com.trocafacil.ems.domain.model;

import br.com.trocafacil.ems.domain.model.account.User;

public record CustomResponseDto<T>(
        T data,
        String error_message
) {}
