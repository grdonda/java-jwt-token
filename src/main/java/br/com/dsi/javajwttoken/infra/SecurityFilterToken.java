package br.com.dsi.javajwttoken.infra;

import br.com.dsi.javajwttoken.authentication.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilterToken extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = getHeaderFromRequest(request);
        var isValidToken = tokenService.validateToken(token);
        if (isValidToken != null) {
            // error
        }

        filterChain.doFilter(request, response);
    }

    private String getHeaderFromRequest(HttpServletRequest request) {
        var xAuthToken = request.getHeader("x-auth-token");
        if (xAuthToken != null && !xAuthToken.trim().isEmpty()) {
            System.out.println("Validar o header: x-auth-token recebido: " + xAuthToken);
            return xAuthToken;

        }
        return xAuthToken;
    }

}
