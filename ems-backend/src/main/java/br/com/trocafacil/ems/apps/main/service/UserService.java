package br.com.trocafacil.ems.apps.main.service;

import br.com.trocafacil.ems.apps.main.repository.UserRepository;
import br.com.trocafacil.ems.config.exception.CustomResponseException;
import br.com.trocafacil.ems.domain.model.CustomResponseDto;
import br.com.trocafacil.ems.domain.model.account.Account;
import br.com.trocafacil.ems.domain.model.account.Address;
import br.com.trocafacil.ems.domain.model.account.Role;
import br.com.trocafacil.ems.domain.model.account.User;
import br.com.trocafacil.ems.domain.model.account.dto.UserCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


@Service
public class UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private AccountService accountService;
    @Autowired private AddressService addressService;
    @Autowired private RoleService roleService;

    @Transactional
    public void adicionarRoleAoUsuario(User user, String role) {
//        user.getAuthorities().add();
    }

    @Transactional
    public ResponseEntity<CustomResponseDto<User>> create(UserCreateDto userCreateDto){
        try{
            Role role = roleService.findByName("ROLE_USER");
            if (role == null){
                Role nRole = new Role();
                nRole.setName("ROLE_USER");
                role = roleService.save(nRole);
            }

            User user = userCreateDto.createUser(role);
            String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(encryptedPassword);

            if(userRepository.findByLogin(user.getLogin()) != null) {
                throw new CustomResponseException(HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), "O Usuário já existe!");
            }

            User userCreated = userRepository.save(user);

            Address address = addressService.save(userCreateDto.createAddress());

            Account account = userCreateDto.createAccount(userCreated, address);

            if (accountService.getAccountByDocumentOrUsername(account.getUsername(), account.getDocument()) != null){
                throw new CustomResponseException(HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), "A Conta já existe!");
            }
            CustomResponseDto<User> response = new CustomResponseDto<User>(accountService.save(userCreateDto.createAccount(userCreated, address)).getUser(), "");
            return ResponseEntity.ok(response);

        }catch (CustomResponseException ex){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponseDto<User>(null, ex.getMessage()));
        }catch (RuntimeException ex){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomResponseDto<User>(null, "Erro interno no servidor"));
        }
    }


    public boolean saveEntity(User user){
        return verifyPassword(user);
    }

    private boolean verifyEmail(User user) {
        var email = user.getLogin();

        boolean emailContainsArroba = email.contains("@");
        boolean emailContainsEnd = email.contains(".com");
        String emailSplit[] = email.split("@");
        boolean emailLengthGreaterThan4 = emailSplit[0].length() > 4;

        return emailContainsArroba && emailContainsEnd && emailLengthGreaterThan4;
    }

    private boolean verifyPassword(User user){
        String password = user.getPassword();

        if (password.length() < 12){
            return false;
        }
        if (!password.contains("@") && !password.contains("!") && !password.contains("?") && !password.contains("#")){
            return false;
        }

        return true;
    }
}
