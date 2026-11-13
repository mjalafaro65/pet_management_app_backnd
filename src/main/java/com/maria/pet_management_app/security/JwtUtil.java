package com.maria.pet_management_app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Component
public class JwtUtil {

    @Value("${spring.jwt.secret}")
    private String secretKeyString;

    @Value("${spring.jwt.expiration}")
    private  long  jwtExpiration;


    //extract email from token
    public String extractEmail(String token){
        return extractAllClaims(token).getSubject();
    }

    //convert string key to cryptographic key
    private SecretKey getSigningKey(){

        return Keys.hmacShaKeyFor(secretKeyString.getBytes(StandardCharsets.UTF_8));
    }

    //generate token from email
    public String generateToken(String email){
        Date now= new Date();
        Date expiry = new Date(now.getTime()+jwtExpiration);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public boolean isTokenValid(String token) {
        try {
            return !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token){
        return extractAllClaims(token).getExpiration().before(new Date());

    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
