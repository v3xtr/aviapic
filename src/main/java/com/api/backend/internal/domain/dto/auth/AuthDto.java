package com.api.backend.internal.domain.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthDto {

    @NotBlank(message = "Имейл обязателен")
    @Email(message = "Email должен быть владным")
    String email;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 6, message = "Пароль должен быть минимум 6 символов")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).*$",
            message = "Пароль должен содержать хотя бы одну цифру и букву")
    String password;

    public AuthDto() {
    }
}

