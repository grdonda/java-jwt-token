package br.com.dsi.javajwttoken.domain.token.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginCredentialsDto(
                @NotBlank String username,
                @NotBlank String password) {
}
