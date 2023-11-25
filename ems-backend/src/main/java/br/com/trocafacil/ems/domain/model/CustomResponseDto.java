package br.com.trocafacil.ems.domain.model;

public record CustomResponseDto<T>(
        T data,
        String error_message
) {}
