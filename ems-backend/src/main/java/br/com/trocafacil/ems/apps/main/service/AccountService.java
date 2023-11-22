package br.com.trocafacil.ems.apps.main.service;

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
        return accountRepository.save(account);
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
