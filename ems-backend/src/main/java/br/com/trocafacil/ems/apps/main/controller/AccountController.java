package br.com.trocafacil.ems.apps.main.controller;

import br.com.trocafacil.ems.apps.auth.service.JwtService;
import br.com.trocafacil.ems.apps.main.repository.AccountRepository;
import br.com.trocafacil.ems.apps.main.repository.AddressRepository;
import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.Address;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private JwtService jwtService;

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/findall")
    public ResponseEntity<ArrayList<Account>> findAll(){

//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzZW5hYy1waSIsInN1YiI6InJpYmVqaG81QHNlbmFjLmNvbS5iciIsImV4cCI6MTY5OTIxMzc4M30.QNfZovuELupzFVNjJdK6889ZPzdRUmOzwIJjEwF_3uQ";
//        jwtService.getUserByToken(token);
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
        Address address = addressRepository.save(account.getAddress());
        account.setAddress(address);
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
