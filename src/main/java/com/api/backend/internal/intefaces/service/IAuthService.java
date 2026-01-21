package com.api.backend.internal.intefaces.service;

import com.api.backend.internal.domain.dto.auth.AuthResponseDTO;
import com.api.backend.internal.domain.dto.auth.AuthResult;

public interface IAuthService {
    AuthResult register(String email, String password);
    AuthResult login(String email, String password);

}