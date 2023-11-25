package br.com.trocafacil.ems.domain.model;

import java.util.List;

public record CustomResponseListDto<T> (
        List<T> data,
        String error_message
    ) {
}
