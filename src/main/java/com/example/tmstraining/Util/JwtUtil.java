package com.example.tmstraining.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.tmstraining.dtos.user.UserDTO;

import java.util.Date;

public class JwtUtil {
    private static final String secretKey = "secret";
    private static final Algorithm algorithm = Algorithm.HMAC256(secretKey);

    public static String generateToken(UserDTO userDTO) {
        String token = JWT.create()
                .withClaim("id", userDTO.getId())
                .withClaim("username", userDTO.getUsername())
                .withClaim("role", userDTO.getRole().name())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + 5 * 60 * 1000))
                .sign(algorithm);

        return token;
    }

    public static DecodedJWT verifyToken(String token) {
        DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);
        return decodedJWT;

    }
}
