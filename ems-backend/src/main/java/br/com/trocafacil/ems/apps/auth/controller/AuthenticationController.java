package br.com.trocafacil.ems.apps.auth.controller;

import br.com.trocafacil.ems.apps.main.repository.RoleRepository;
import br.com.trocafacil.ems.apps.main.service.UserService;
import br.com.trocafacil.ems.domain.model.account.Role;
import br.com.trocafacil.ems.domain.model.account.User;
import br.com.trocafacil.ems.domain.model.account.dto.UserCreateDto;
import br.com.trocafacil.ems.domain.model.token.TokenDto;
import br.com.trocafacil.ems.apps.auth.repository.LoginRepository;
import br.com.trocafacil.ems.apps.auth.service.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private LoginRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;



    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Validated User user){
        var token = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());

        // Por debaixo dos panos, esse objeto do spring chama o AuthenticationService
        var authentication = authenticationManager.authenticate(token);
        String tokenJwt = jwtService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenDto(tokenJwt, (Set<Role>) user.getAuthorities()));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<User> register(@RequestBody @Validated UserCreateDto userCreateDto){

        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null){
            Role nRole = new Role();
            nRole.setName("ROLE_USER");
            role = roleRepository.save(nRole);
        }

        User user = userCreateDto.createUser(role);
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        if(userRepository.findByLogin(user.getLogin()) != null) return ResponseEntity.badRequest().build();
        this.userRepository.save(user);
        return ResponseEntity.ok(user);

    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<User> create(@RequestBody User user){
//        Role role = roleRepository.findByName("ROLE_USER");
//        if (role == null){
//            Role nRole = new Role();
//            nRole.setName("ROLE_USER");
//            role = roleRepository.save(nRole);
//        }
//        User user = userCreateDto.createUser(role);
//        if(userRepository.findByLogin(user.getLogin()) != null) return ResponseEntity.badRequest().build();
//        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
//        User login = new User(user.getLogin(), encryptedPassword);
//        this.userRepository.save(login);
//        return ResponseEntity.ok(login);

        return ResponseEntity.notFound().build();
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
