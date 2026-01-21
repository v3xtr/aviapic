package com.api.backend.internal.domain.dto.user;

import com.api.backend.internal.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String password;

    public static UserResponse fromEntity(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getPassword());
    }
}