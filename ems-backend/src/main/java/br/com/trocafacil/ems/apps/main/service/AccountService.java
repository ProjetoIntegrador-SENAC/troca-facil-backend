package br.com.trocafacil.ems.apps.main.service;

import br.com.trocafacil.ems.apps.main.repository.AccountRepository;
import br.com.trocafacil.ems.domain.model.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

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

    public Account getAccountByUserId(Long id){
        return accountRepository.findByUserId(id);
    }
}
