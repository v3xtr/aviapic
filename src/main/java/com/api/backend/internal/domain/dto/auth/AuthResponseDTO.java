package com.api.backend.internal.domain.dto.auth;

import com.api.backend.internal.domain.dto.user.UserResponse;
import com.api.backend.internal.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    private UserResponse user;

    public AuthResponseDTO() {
    }

    public static AuthResponseDTO of(User user) {
        return new AuthResponseDTO(UserResponse.fromEntity(user));
    }
}