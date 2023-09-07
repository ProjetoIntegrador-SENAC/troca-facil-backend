package br.com.trocafacil.authentication.model.controller;

import br.com.trocafacil.authentication.model.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity login(@RequestBody @Validated LoginDto login){
        var token = new UsernamePasswordAuthenticationToken(login.login(), login.password());

        // Por debaixo dos panos, esse objeto do spring chama o AuthenticationService
        var authentication = authenticationManager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}
