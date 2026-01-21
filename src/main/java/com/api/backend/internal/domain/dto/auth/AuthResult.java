package com.api.backend.internal.domain.dto.auth;

import com.api.backend.internal.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResult {
    private User user;
    private String accessToken;
    private String refreshToken;
}