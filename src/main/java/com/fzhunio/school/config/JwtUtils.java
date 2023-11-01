package com.fzhunio.school.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.expiration.time}")
    private  String timeExpiration;

    public String generateToken(String username) {
        var builder = Jwts.builder();
        builder.subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)));
        return builder.signWith(getSignatureKey(), Jwts.SIG.HS256).compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().verifyWith(getSignatureKey())
                    .build().parseSignedClaims(token)
                    .getPayload();
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public Claims allClaims(String token) {
        return Jwts.parser().verifyWith(getSignatureKey())
                .build().parseSignedClaims(token)
                .getPayload();
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = allClaims(token);
        return claimsTFunction.apply(claims);
    }

    public SecretKey getSignatureKey() {
        byte[] key = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }
}
