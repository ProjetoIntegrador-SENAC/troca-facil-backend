package br.com.trocafacil.model.service;

import br.com.trocafacil.model.dto.AccountDto;
import br.com.trocafacil.model.entity.Account;
import br.com.trocafacil.model.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account save(AccountDto account){
        Account account1 = new Account(account);

        return accountRepository.save(account1);
    }

    public void deleteById(String uuid){
        accountRepository.deleteById(uuid);
    }

    public Account findById(String uuid){
       return accountRepository.findById(uuid).get();
    }

    public Account findByDocument(String document){
        return accountRepository.findByDocument(document);
    }
}
