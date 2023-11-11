package br.com.trocafacil.ems.apps.main.controller;

import br.com.trocafacil.ems.apps.auth.service.JwtService;
import br.com.trocafacil.ems.apps.main.repository.AccountRepository;
import br.com.trocafacil.ems.apps.main.repository.AddressRepository;
import br.com.trocafacil.ems.apps.main.repository.UserRepository;
import br.com.trocafacil.ems.apps.main.service.MyBlobService;
import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.Address;
import br.com.trocafacil.ems.domain.model.account.User;
import br.com.trocafacil.ems.domain.model.account.dto.AccountCreateDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.WritableResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("account")
@Slf4j
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
public class AccountController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyBlobService myBlobService;

    @GetMapping("/findall")
    public ResponseEntity<ArrayList<Account>> findAll(){

        ArrayList<Account> accounts = (ArrayList<Account>) this.accountRepository.findAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Account> find(@PathVariable Long id){
        Optional<Account> account = this.accountRepository.findById(id);
        if (account.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account.get());
    }

    @PostMapping("/create")
    public ResponseEntity<Account> create(@Valid @RequestBody AccountCreateDto accountDto, @AuthenticationPrincipal User user) {
        Account account = accountDto.createAccount(user);
        addressRepository.save(accountDto.address());
        Account accCreated = accountRepository.save(account);
        return ResponseEntity.ok(accCreated);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try {
            this.accountRepository.deleteById(id);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Account> update(@RequestBody @Valid Account account){
        Account account1 = this.accountRepository.save(account);
        return ResponseEntity.ok(account1);
    }


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) throws IOException {
        log.info("Filename: " + file.getOriginalFilename());
        log.info("Size:" + file.getSize());
        log.info("Content-type: " + file.getContentType());
        var idLong = Long.parseLong(id);
        myBlobService.storeFile(file.getOriginalFilename(),file.getInputStream(), file.getSize(), idLong);
        return file.getOriginalFilename() + " Has been saved as a blob-item!!!";
    }

}
