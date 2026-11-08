package com.pi.frioVale.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Cadastro público. O primeiro usuário do sistema vira administrador;
 * os seguintes entram como motoristas.
 */
public record RegisterRequest(
        @NotBlank(message = "O nome é obrigatório") String name,
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "O e-mail informado é inválido") String email,
        String phone,
        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres") String password
) {
}
