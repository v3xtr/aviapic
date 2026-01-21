package com.api.backend.pkg;

import com.api.backend.internal.domain.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Component
public class JWTBuilder {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public HashMap<String, String> generateTokens(User user){
        io.jsonwebtoken.JwtBuilder builder = Jwts.builder();
        builder.claim("id", user.getId());
        builder.signWith(
                SignatureAlgorithm.HS256,
                jwtSecret.getBytes(StandardCharsets.UTF_8)
        );

        String accessToken = builder.compact();
        String refreshToken = builder.compact();

        HashMap<String, String> responseObject = new HashMap<>();

        responseObject.put("accessToken", accessToken);
        responseObject.put("refreshToken", refreshToken);

        return responseObject;
    }

}
