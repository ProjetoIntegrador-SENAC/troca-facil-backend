package br.com.trocafacil.principal.model.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloController {

    // @Value("${MSG_EXIBIR}")
    // private String msgExibir;

    @GetMapping("/")
    public ResponseEntity<String> getHello(){
        String msgExibir = System.getenv("MSG_EXIBIR");
        return ResponseEntity.ok("Ai sim! " + msgExibir);
    }
}
