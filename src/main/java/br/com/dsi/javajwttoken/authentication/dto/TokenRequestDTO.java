package br.com.dsi.javajwttoken.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record TokenRequestDTO(

        @NotBlank
        String username,

        @NotBlank
        @Email
        String email) {
}
