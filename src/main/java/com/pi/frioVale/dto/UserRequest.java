package com.pi.frioVale.dto;

import com.pi.frioVale.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Cadastro de usuário. Quando {@code password} não é informado, o backend
 * gera automaticamente uma senha inicial (hash BCrypt).
 */
public record UserRequest(
        @NotBlank(message = "O nome é obrigatório") String name,
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "O e-mail informado é inválido") String email,
        String phone,
        @NotNull(message = "O perfil é obrigatório") Role role,
        String password
) {
}
