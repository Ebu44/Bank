package com.mini_bank.security;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtTokenService {
    private static final String SECRET_KEY = "a2a210g859N41pF8Ibovl4qIsGCEOXJqJGJ7MzL148CbQAGuEOtJjtGn3xKW";

    private static final int TOKEN_VALIDITY = 8*60*60*1000;

    public String generateToken(String userEmail){
        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token, String uEmail){
        final String userEmail = extractUserEmail(token);
        return (userEmail.equals(uEmail) && !isTokenExpired(token));
    }

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Date extractExpiration(String token) { return extractClaim(token, Claims::getExpiration);}

    private String extractUserEmail(String token) { return extractClaim(token, Claims::getSubject);}

    private boolean isTokenExpired(String token) { return extractExpiration(token).before(new Date());}
}
