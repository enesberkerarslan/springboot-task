package com.JavaRestfulApi.springboottask.JwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenProvider {
    private final String secretKey = "amadeusjavaspringtask";
    private final long validityInMilliseconds = 3600000;

    public String createToken(String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Token geçerli değilse veya bozuksa, false döner.
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
