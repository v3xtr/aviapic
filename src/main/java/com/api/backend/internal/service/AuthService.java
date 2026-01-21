package com.api.backend.internal.service;

import com.api.backend.internal.domain.dto.auth.AuthResult;
import com.api.backend.internal.domain.models.User;
import com.api.backend.internal.intefaces.repo.IUserRepo;
import com.api.backend.internal.intefaces.service.IAuthService;
import com.api.backend.pkg.JWTBuilder;
import com.api.backend.pkg.UserAlreadyExistsException;
import com.api.backend.pkg.UserDontExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final IUserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTBuilder jwtBuilder;

    @Transactional
    public AuthResult register(String email, String password) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email не может быть пустым");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Пароль не может быть пустым");
        }

        String cleanEmail = email.trim().toLowerCase();

        userRepo.findByEmail(cleanEmail).ifPresent(user -> {
            throw new UserAlreadyExistsException("Пользователь уже существует");
        });

        User user = new User();
        user.setEmail(cleanEmail);
        user.setPassword(passwordEncoder.encode(password));

        User savedUser = userRepo.save(user);
        HashMap<String, String> tokens = jwtBuilder.generateTokens(savedUser);

        return new AuthResult(savedUser,
                tokens.get("accessToken"),
                tokens.get("refreshToken")
        );
    }

    public AuthResult login(String email, String password) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email не может быть пустым");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Пароль не может быть пустым");
        }

        String cleanEmail = email.trim().toLowerCase();

        User user = userRepo.findByEmail(cleanEmail)
                .orElseThrow(() -> new UserDontExistException("Пользователь не найден"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Неверный пароль");
        }

        HashMap<String, String> tokens = jwtBuilder.generateTokens(user);

        return new AuthResult(user,
                tokens.get("accessToken"),
                tokens.get("refreshToken")
        );
    }
}