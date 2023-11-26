package br.com.trocafacil.ems.apps.main.controller;

import br.com.trocafacil.ems.apps.main.service.AccountService;
import br.com.trocafacil.ems.apps.main.service.AddressService;
import br.com.trocafacil.ems.apps.main.service.MyBlobService;
import br.com.trocafacil.ems.apps.main.service.PhotoService;
import br.com.trocafacil.ems.domain.model.CustomResponseListDto;
import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.User;
import br.com.trocafacil.ems.domain.model.account.dto.AccountCreateDto;
import br.com.trocafacil.ems.domain.model.photo.Photo;
import br.com.trocafacil.ems.domain.model.photo.enums.PhotoEnum;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("account")
@Slf4j
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private MyBlobService myBlobService;

    @Autowired
    private PhotoService photoService;

    @GetMapping("/findall")
    public ResponseEntity<CustomResponseListDto<Account>> findAll(){
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Account> find(@PathVariable Long id){
        var account = accountService.findById(id);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/create")
    public ResponseEntity<Account> create(@Valid @RequestBody AccountCreateDto accountDto, @AuthenticationPrincipal User user) {
        Account account = accountDto.createAccount(user);
        addressService.save(accountDto.address());
        Account accCreated = accountService.save(account);
        return ResponseEntity.ok(accCreated);
    }

    @PostMapping("/find/photo/{id}")
    public ResponseEntity<String> getPhotoPath(@PathVariable Long id){
        Photo photo = photoService.findByExternalIdAndAccountProduct(id, PhotoEnum.ACCOUNT.name()).get();
        return ResponseEntity.ok().body(photo.getPhotoPath());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try {
            this.accountService.deleteById(id);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Account> update(@RequestBody @Valid Account account){
        Account account1 = this.accountService.save(account);
        return ResponseEntity.ok(account1);
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) throws IOException {
        log.info("Filename: " + file.getOriginalFilename());
        log.info("Size:" + file.getSize());
        log.info("Content-type: " + file.getContentType());
        var idLong = Long.parseLong(id);
        myBlobService.storeAccountFile(file.getOriginalFilename(),file.getInputStream(), file.getSize(), idLong);
        var response =  file.getOriginalFilename() + " Has been saved as a blob-item!!!";
        return ResponseEntity.ok().body(response);
    }

}
