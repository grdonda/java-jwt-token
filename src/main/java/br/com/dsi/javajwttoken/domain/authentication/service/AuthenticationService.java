package br.com.dsi.javajwttoken.domain.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import br.com.dsi.javajwttoken.domain.token.dto.LoginCredentialsDto;
import br.com.dsi.javajwttoken.domain.token.dto.TokenDto;
import br.com.dsi.javajwttoken.domain.token.service.TokenService;

@Service
public class AuthenticationService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public TokenDto login(LoginCredentialsDto data) {
        try {
            System.out.println("login: autenticando dados -> " + data);

            var token = new UsernamePasswordAuthenticationToken(data.username(), data.password());
            System.out.println("login: user and pass token -> " + token);

            var authentication = authenticationManager.authenticate(token);
            System.out.println("login: authentication -> " + authentication);

            var tokenJWT = tokenService.createToken(data);
            System.out.println("login: token created ->" + tokenJWT);

            return new TokenDto(tokenJWT);
        } catch (Exception e) {
            System.out.println("Erro ao autenticar: " + e.getMessage());
            throw e;
        }
    }
}
