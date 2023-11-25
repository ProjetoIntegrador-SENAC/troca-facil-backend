package br.com.trocafacil.ems.apps.auth.service;

import br.com.trocafacil.ems.apps.main.repository.UserRepository;
import br.com.trocafacil.ems.domain.model.account.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Autowired
    private UserRepository userRepository;

    @Value("${api.security.token.secret}")
    private String secret;

    public User getUserByToken(String token){

        String login  = JWT.decode(token).getClaim("sub").asString();
        User user = userRepository.findByLogin(login);

        return null;
    }

    public String generateToken(User user){

        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("senac-pi")
                    .withSubject(user.getLogin())
                    .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                    .withClaim("roles", roles)
                    .withClaim("username", user.getUsername())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e){
            throw new RuntimeException("Não foi possível gerar o token JWT");
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("senac-pi")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido: " + token);
        }

    }
}
