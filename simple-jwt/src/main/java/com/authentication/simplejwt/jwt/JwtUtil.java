package com.authentication.simplejwt.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    // Get UserName from JwtToken
    public String getUsernameFromToken(String jwtToken) {
        return getClaimsFromToken(jwtToken, Claims::getSubject);
    }

    // Generate JwtToken
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // Check the validity of Token
    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        String username = getUsernameFromToken(jwtToken);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

    private boolean isTokenExpired(String jwtToken) {
        Date expirationDate = getExpirationDateFromToken(jwtToken);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDateFromToken(String jwtToken) {
        return getClaimsFromToken(jwtToken, Claims::getExpiration);
    }

    // This method is Handy for retrieving all the claims from Jwt Token
    private <T> T getClaimsFromToken(String jwtToken, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(jwtToken);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwtToken)
                .getBody();
    }

}
