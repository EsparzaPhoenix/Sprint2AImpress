package br.com.fiap.aimpress.service;

import br.com.fiap.aimpress.model.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.token.secret}") //Valor configurado no application.properties
    private String senha;

    public String getSubject(String tokenJwt){
        try {
            Algorithm algorithm = Algorithm.HMAC256(senha);
            return JWT.require(algorithm)
                    .withIssuer("FIAP")
                    .build()
                    .verify(tokenJwt)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Não foi possivel validar o token JWT");
        }
    }

    public String gerarToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(senha);
            return JWT.create()
                    .withIssuer("FIAP")
                    .withSubject(user.getLogin())
                    .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                    .sign(algorithm);
        } catch (JWTCreationException e){
            throw new RuntimeException("Erro ao criar o token JWT");
        }
    }

}
