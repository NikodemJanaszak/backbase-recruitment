package com.backbase.recruitment.security;

import com.backbase.recruitment.model.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class TokenGenerator {

    @Value("${token.secret}")
    private String SECRET;

    public String generateToken(User user) {
        return Jwts.builder().
                setSubject(user.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(30)))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
}
