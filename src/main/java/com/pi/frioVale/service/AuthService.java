package com.pi.frioVale.service;

import com.pi.frioVale.config.JwtProperties;
import com.pi.frioVale.domain.User;
import com.pi.frioVale.dto.AuthResponse;
import com.pi.frioVale.dto.LoginRequest;
import com.pi.frioVale.dto.RegisterRequest;
import com.pi.frioVale.dto.UserResponse;
import com.pi.frioVale.exception.BusinessRuleException;
import com.pi.frioVale.repository.UserRepository;
import com.pi.frioVale.security.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

/** Regras de autenticação: login, cadastro e emissão do token JWT. */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;
    private final JwtProperties jwtProperties;

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        String email = request.email().trim().toLowerCase(Locale.ROOT);
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(AuthService::invalidCredentials);

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw invalidCredentials();
        }

        return response(user);
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        UserResponse created = userService.register(request);
        User user = userRepository.findById(created.id())
                .orElseThrow(AuthService::invalidCredentials);
        return response(user);
    }

    private AuthResponse response(User user) {
        JwtTokenService.IssuedToken issued = jwtTokenService.issue(user);
        return AuthResponse.bearer(issued.token(), jwtProperties.expirationMinutes(), UserResponse.from(user));
    }

    private static BusinessRuleException invalidCredentials() {
        return new BusinessRuleException("E-mail ou senha inválidos", HttpStatus.UNAUTHORIZED);
    }
}
