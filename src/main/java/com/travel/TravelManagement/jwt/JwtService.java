package com.travel.TravelManagement.jwt;

import com.travel.TravelManagement.model.Registration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtService {

    private final String SECRET =
            "mysupersecretkeymysupersecretkeymysupersecretkey";

    // Generate Token
    public String generateToken(Registration user) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()),
                        SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract All Claims
    public Claims extractClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Get User Id
    public Long getUserId(String token) {
        return extractClaims(token).get("id", Long.class);
    }

    // Get Email
    public String getEmail(String token) {
        return extractClaims(token).getSubject();
    }

    // Get Role
    public String getRole(String token) {
        return extractClaims(token).get("role", String.class);
    }


    // Validate Token
    public boolean validateToken(String token) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {
            return false;
        }
    }
    
}
