package br.com.trocafacil.authentication.model.controller;

import br.com.trocafacil.authentication.infra.security.JwtService;
import br.com.trocafacil.authentication.model.entity.User;
import br.com.trocafacil.authentication.model.entity.dto.TokenDto;
import br.com.trocafacil.authentication.model.repository.LoginRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private LoginRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Validated User login){
        var token = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());

        // Por debaixo dos panos, esse objeto do spring chama o AuthenticationService
        var authentication = authenticationManager.authenticate(token);

        String tokenJwt = jwtService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDto(tokenJwt));

    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<User> register(@RequestBody @Validated User user){

        if(userRepository.findByLogin(user.getLogin()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());

        var login = new User(user.getLogin(), encryptedPassword);

        this.userRepository.save(login);

//        AccountDto account = new AccountDto(login.name(), login.surname(), user, login.document());
//
//        this.accountService.save(account);
//
//        // TESTE BÁSICO PARA O FUNCIONAMENTO DE UM PRODUTO
//
//        var account1 = this.accountService.findByDocument(login.document());
//        Product p1 = new Product(null, "Teste", "Teste", "teste", "teste", account1);
//        this.productRepository.save(p1);
        
        return ResponseEntity.ok(login);
    }

    @GetMapping("/token")
    public ResponseEntity validateToken(@RequestParam("token") String token){
        try{
            jwtService.validateToken(token);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Token inválido!");
        }
        return ResponseEntity.ok().build();
    }
}
