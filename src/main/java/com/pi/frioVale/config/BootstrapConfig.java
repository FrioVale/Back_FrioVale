package com.pi.frioVale.config;

import com.pi.frioVale.domain.User;
import com.pi.frioVale.domain.enums.Role;
import com.pi.frioVale.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.util.Locale;

/**
 * Garante que o administrador inicial exista no banco, usando as propriedades
 * {@code friovale.bootstrap.admin-*}. Assim a tela de perfil/equipe sempre tem
 * um usuário real vindo do banco, sem valores fixos no front.
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class BootstrapConfig {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public ApplicationRunner bootstrapAdmin(
            @Value("${friovale.bootstrap.admin-name:Gestor}") String name,
            @Value("${friovale.bootstrap.admin-email:gestor@friovale.com}") String email,
            @Value("${friovale.bootstrap.admin-password:}") String password) {

        return args -> {
            if (userRepository.count() > 0 || !StringUtils.hasText(password)) {
                return;
            }
            User admin = User.builder()
                    .name(name)
                    .email(email.trim().toLowerCase(Locale.ROOT))
                    .password(passwordEncoder.encode(password))
                    .role(Role.ROLE_COMPANY)
                    .build();
            userRepository.save(admin);
            log.info("Administrador inicial criado: {}", admin.getEmail());
        };
    }
}
