package com.pi.frioVale.config;

import com.pi.frioVale.domain.User;
import com.pi.frioVale.domain.enums.Role;
import com.pi.frioVale.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Locale;


@Slf4j
@Component
@RequiredArgsConstructor
public class AdminBootstrap implements ApplicationRunner {

    private final BootstrapProperties props;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        if (!StringUtils.hasText(props.adminEmail()) || !StringUtils.hasText(props.adminPassword())) {
            return;
        }
        if (userRepository.existsByRole(Role.ROLE_COMPANY)) {
            return;
        }
        User admin = User.builder()
                .name(props.adminName())
                .email(props.adminEmail().trim().toLowerCase(Locale.ROOT))
                .password(passwordEncoder.encode(props.adminPassword()))
                .role(Role.ROLE_COMPANY)
                .build();
        userRepository.save(admin);
        log.info("Gestor inicial criado: {}", admin.getEmail());
    }
}
