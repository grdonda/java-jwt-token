package br.com.dsi.javajwttoken.domain.token.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.dsi.javajwttoken.domain.token.dto.LoginCredentialsDto;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    public String createToken(LoginCredentialsDto data) {

        try {
            var algorithm = getAlgorithm();
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(data.username())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("error to create token data", exception);
        }
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    public String getSubject(String token) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado: " + token);
        }
    }
}
