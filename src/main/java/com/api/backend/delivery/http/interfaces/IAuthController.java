package com.api.backend.delivery.http.interfaces;

import com.api.backend.internal.domain.dto.auth.ApiResponse;
import com.api.backend.internal.domain.dto.auth.AuthDto;
import com.api.backend.internal.domain.dto.auth.AuthResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthController {
    ResponseEntity<ApiResponse<AuthResponseDTO>> register(@RequestBody @Valid AuthDto authDto,
                                                          HttpServletResponse response);
    ResponseEntity<ApiResponse<AuthResponseDTO>> login(@RequestBody @Valid AuthDto authDto, HttpServletResponse response);
}


