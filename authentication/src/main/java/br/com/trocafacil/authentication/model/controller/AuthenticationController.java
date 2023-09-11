package br.com.trocafacil.authentication.model.controller;

import br.com.trocafacil.authentication.infra.security.JwtService;
import br.com.trocafacil.model.dto.AccountDto;
import br.com.trocafacil.model.dto.LoginDto;
import br.com.trocafacil.model.dto.TokenDto;
import br.com.trocafacil.model.entity.Account;
import br.com.trocafacil.model.entity.User;
import br.com.trocafacil.model.repository.UserRepository;
import br.com.trocafacil.model.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Validated LoginDto login){
        var token = new UsernamePasswordAuthenticationToken(login.login(), login.password());

        // Por debaixo dos panos, esse objeto do spring chama o AuthenticationService
        var authentication = authenticationManager.authenticate(token);

        String tokenJwt = jwtService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDto(tokenJwt));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity register(@RequestBody @Validated LoginDto login){
        if(userRepository.findByLogin(login.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(login.password());

        User user = new User(login.login(), encryptedPassword);

        this.userRepository.save(user);

        AccountDto account = new AccountDto(login.name(), login.surname(), user, login.document());

        this.accountService.save(account);

        return ResponseEntity.ok().build();
    }
}
