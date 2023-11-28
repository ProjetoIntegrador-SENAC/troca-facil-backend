package br.com.trocafacil.ems.apps.main.service;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.trocafacil.ems.apps.main.repository.AccountRepository;
import br.com.trocafacil.ems.domain.model.CustomResponseListDto;
import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.dto.AccountPhotoDto;
import br.com.trocafacil.ems.domain.model.photo.Photo;
import br.com.trocafacil.ems.domain.model.photo.enums.PhotoEnum;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PhotoService photoService;

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
        //verifyDocument(account);
        return accountRepository.save(account);
    }

    @Transactional
    public Account findByUsername(String username){
        Optional<Account> optionalAccount = this.accountRepository.findByUsername(username);
        if (!optionalAccount.isPresent()) throw new EntityNotFoundException("Conta não encontrada!");
        Account account = optionalAccount.get();
        return account;
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
        photoService.saveImage(id, imagePath, PhotoEnum.ACCOUNT.name());
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
