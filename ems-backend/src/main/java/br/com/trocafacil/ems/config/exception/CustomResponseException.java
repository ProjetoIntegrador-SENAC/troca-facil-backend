package br.com.trocafacil.ems.config.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class CustomResponseException extends ResponseStatusException {

    public CustomResponseException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
