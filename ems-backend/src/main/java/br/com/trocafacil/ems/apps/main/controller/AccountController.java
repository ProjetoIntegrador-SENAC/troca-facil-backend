package br.com.trocafacil.ems.apps.main.controller;

import br.com.trocafacil.ems.apps.main.repository.AccountRepository;
import br.com.trocafacil.ems.domain.model.account.Account;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/findall")
    public ResponseEntity<ArrayList<Account>> findAll(){
        ArrayList<Account> accounts = (ArrayList<Account>) this.accountRepository.findAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Account> find(@PathVariable String id){
        Optional<Account> account = this.accountRepository.findById(id);
        if (account.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account.get());
    }

    @PostMapping("/create")
    public ResponseEntity<Account> create(@Valid @RequestBody Account account){
        Account accCreated = this.accountRepository.save(account);
        return ResponseEntity.ok(accCreated);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
        try {
            this.accountRepository.deleteById(id);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
        }
}
