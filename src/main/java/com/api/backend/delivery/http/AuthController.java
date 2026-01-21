package com.api.backend.delivery.http;

import com.api.backend.delivery.http.interfaces.IAuthController;
import com.api.backend.internal.domain.dto.auth.ApiResponse;
import com.api.backend.internal.domain.dto.auth.AuthDto;
import com.api.backend.internal.domain.dto.auth.AuthResponseDTO;
import com.api.backend.internal.domain.dto.auth.AuthResult;
import com.api.backend.internal.service.AuthService;
import com.api.backend.pkg.Cookies;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController implements IAuthController {

    private final AuthService authService;
    private final Cookies cookies;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> register(@RequestBody @Valid AuthDto authDto,
                                                                 HttpServletResponse response) {

        AuthResult authResult = authService.register(
                authDto.getEmail(),
                authDto.getPassword()
        );

        cookies.setAuthCookies(response,
                authResult.getAccessToken(),
                authResult.getRefreshToken()
        );

        AuthResponseDTO authResponse = AuthResponseDTO.of(authResult.getUser());

        return ResponseEntity.ok(ApiResponse.success("Вы успешно зарегистрировались", authResponse));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> login(
            @RequestBody @Valid AuthDto authDto,
            HttpServletResponse response) {

        AuthResult authResult = authService.login(
                authDto.getEmail(),
                authDto.getPassword()
        );

        cookies.setAuthCookies(response,
                authResult.getAccessToken(),
                authResult.getRefreshToken()
        );

        AuthResponseDTO authResponse = AuthResponseDTO.of(authResult.getUser());

        return ResponseEntity.ok(ApiResponse.success("Вы успешно вошли", authResponse));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(HttpServletResponse response) {
        cookies.clearAuthCookies(response);

        return ResponseEntity.ok(ApiResponse.success("Вы успешно вышли", null));
    }
}