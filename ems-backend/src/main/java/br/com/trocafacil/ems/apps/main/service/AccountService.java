package br.com.trocafacil.ems.apps.main.service;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.trocafacil.ems.apps.main.repository.AccountRepository;
import br.com.trocafacil.ems.config.exception.CustomResponseException;
import br.com.trocafacil.ems.domain.model.CustomResponseDto;
import br.com.trocafacil.ems.domain.model.CustomResponseListDto;
import br.com.trocafacil.ems.domain.model.account.Account;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public CustomResponseListDto<Account> findAll(){
        List<Account> accounts = this.accountRepository.findAll();
        var response = new CustomResponseListDto<Account>(accounts, "");
        return response;
    }

    @Transactional
    public Account findById(Long id){
        Optional<Account> account = this.accountRepository.findById(id);
        if (account.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return account.get();
    }

    @Transactional
    public Account save(Account account){
        verifyDocument(account);
        return accountRepository.save(account);
    }


    public boolean saveEntity(Account account){
        return verifyDocument(account);
    }
    private boolean verifyDocument(Account account){
        CPFValidator validator = new CPFValidator();

        try{
            validator.assertValid(account.getDocument());
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Transactional
    public void deleteById(long id){
        accountRepository.deleteById(id);
    }

    @Transactional
    public String saveImage(Long id, String imagePath){
        Optional<Account> accountOptional = accountRepository.findById(id);

        if (accountOptional.isPresent()){
            Account account = accountOptional.get();
            account.setPhotoPath(imagePath);

            accountRepository.save(account);
        } else {
            return "Account with id: " + id + " doesn't match with anyone in database";
        }

        return "Profile image saved with success!";
    }

    @Transactional
    public Account getAccountByUserId(Long id){
        return accountRepository.findByUserId(id);
    }

    @Transactional
    public Account getAccountByDocumentOrUsername(String username, String document){
        return accountRepository.findByUsernameOrDocument(username, document);
    }

}
