package br.com.dsi.javajwttoken.authentication.service;

import br.com.dsi.javajwttoken.authentication.dto.TokenJwtDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public static final String SECRET = "secret";
    public static final String ISSUER = "issuer";

    public TokenJwtDTO getTokenJWT(String username) {

        Algorithm algoritimo = getAlgorithm();
        String token = JWT.create()
                .withSubject(username)
                .withIssuer(ISSUER)
                .sign(algoritimo);

        return new TokenJwtDTO(token);
    }

    public DecodedJWT validateTokenJWT(TokenJwtDTO token) {

        Algorithm algorithm = getAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();

        return verifier.verify(token.token());
    }


    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(SECRET);
    }

    public Boolean validateToken(String token) {
        System.out.println("Token: " + token);
        return true;
    }
}
