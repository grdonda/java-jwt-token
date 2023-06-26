package br.com.dsi.javajwttoken.authentication;

import br.com.dsi.javajwttoken.authentication.dto.TokenJwtDTO;
import br.com.dsi.javajwttoken.authentication.dto.TokenRequestDTO;
import br.com.dsi.javajwttoken.authentication.dto.TokenValidateDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    public static final String SECRET = "secret";
    public static final String ISSUER = "issuer";

    @PostMapping("/token")
    public ResponseEntity<TokenJwtDTO> getToken(@RequestBody @Valid TokenRequestDTO data) {

        String username = data.username();

        try {
            Algorithm algoritimo = getAlgorithm();
            String token = JWT.create()
                    .withSubject(username)
                    .withIssuer(ISSUER)
                    .sign(algoritimo);

            TokenJwtDTO tokenJwtDTO = new TokenJwtDTO(token);

            return ResponseEntity.ok(tokenJwtDTO);
        } catch (JWTCreationException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/token/validate")
    public ResponseEntity<TokenJwtDTO> validateToken(@RequestBody @Valid TokenValidateDTO token) {

        DecodedJWT decodedJWT;
        try {

            Algorithm algorithm = getAlgorithm();
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            decodedJWT = verifier.verify(token.token());
            return ResponseEntity.ok(new TokenJwtDTO(decodedJWT.getToken()));
        } catch (JWTVerificationException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(SECRET);
    }
}
