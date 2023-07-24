package br.com.dsi.javajwttoken.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsi.javajwttoken.domain.authentication.service.AuthenticationService;
import br.com.dsi.javajwttoken.domain.token.dto.LoginCredentialsDto;
import br.com.dsi.javajwttoken.domain.token.dto.TokenDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService auth;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginCredentialsDto data) {
        return ResponseEntity.ok(auth.login(data));
    }

    
}
