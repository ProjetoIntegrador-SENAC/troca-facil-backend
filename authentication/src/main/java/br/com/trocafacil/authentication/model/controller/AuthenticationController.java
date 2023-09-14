package br.com.trocafacil.authentication.model.controller;

import br.com.trocafacil.authentication.infra.security.JwtService;
import br.com.trocafacil.model.dto.AccountDto;
import br.com.trocafacil.model.dto.LoginDto;
import br.com.trocafacil.model.dto.TokenDto;
import br.com.trocafacil.model.entity.Account;
import br.com.trocafacil.model.entity.Product;
import br.com.trocafacil.model.entity.User;
import br.com.trocafacil.model.repository.ProductRepository;
import br.com.trocafacil.model.repository.UserRepository;
import br.com.trocafacil.model.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private ProductRepository productRepository;

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
    public ResponseEntity<List<Product>> register(@RequestBody @Validated LoginDto login){
        if(userRepository.findByLogin(login.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(login.password());

        User user = new User(login.login(), encryptedPassword);

        this.userRepository.save(user);

        AccountDto account = new AccountDto(login.name(), login.surname(), user, login.document());

        this.accountService.save(account);

        // TESTE BÁSICO PARA O FUNCIONAMENTO DE UM PRODUTO

        var account1 = this.accountService.findByDocument(login.document());
        Product p1 = new Product(null, "Teste", "Teste", "teste", "teste", account1);
        this.productRepository.save(p1);
        
        return ResponseEntity.ok(account1.getProducts());
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
