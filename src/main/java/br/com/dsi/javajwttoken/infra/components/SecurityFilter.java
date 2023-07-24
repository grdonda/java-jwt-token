package br.com.dsi.javajwttoken.infra.components;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.dsi.javajwttoken.domain.token.service.TokenService;
import br.com.dsi.javajwttoken.domain.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@EnableWebSecurity
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recoveryTokenFromRequest(request);

        if (tokenJWT != null) {
            System.out.println("tokenJWT: " + tokenJWT);
            var subject = tokenService.getSubject(tokenJWT);
            var usuario = repository.loadUserByUsername(subject);
            var authentication = new UsernamePasswordAuthenticationToken(
                    usuario,
                    null,
                    usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoveryTokenFromRequest(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("x-auth-token");
        if (authorizationHeader != null) {
            return authorizationHeader.trim();
        }

        return null;
    }
}
