package br.com.trocafacil.ems.apps.main.service;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.trocafacil.ems.apps.main.repository.AccountRepository;
import br.com.trocafacil.ems.domain.model.account.Account;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PhotoService photoService;

    @Transactional
    public ArrayList<Account> findAll(){
        return (ArrayList<Account>) accountRepository.findAll();
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
        photoService.saveImage(id, imagePath, "ACCOUNT");
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
