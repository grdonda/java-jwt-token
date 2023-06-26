package br.com.dsi.javajwttoken.authentication;

import br.com.dsi.javajwttoken.authentication.dto.TokenJwtDTO;
import br.com.dsi.javajwttoken.authentication.dto.TokenRequestDTO;
import br.com.dsi.javajwttoken.authentication.dto.TokenValidateDTO;
import br.com.dsi.javajwttoken.authentication.service.TokenService;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity<TokenJwtDTO> getToken(@RequestBody @Valid TokenRequestDTO data) {

        String username = data.username();

        try {

            TokenJwtDTO tokenJWT = tokenService.getTokenJWT(username);
            return ResponseEntity.ok(tokenJWT);
        } catch (JWTCreationException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/token/validate")
    public ResponseEntity<TokenJwtDTO> validateToken(@RequestBody @Valid TokenValidateDTO token) {
        DecodedJWT decodedJWT;
        try {
            decodedJWT = tokenService.validateTokenJWT(token.token());
            return ResponseEntity.ok(new TokenJwtDTO(decodedJWT.getToken()));
        } catch (JWTVerificationException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

}
