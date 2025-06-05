//package com.ScrapBridge.dto;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JwtTokenProvider {
//    @Value("${app.jwt-secret}")
//    private String jwtSecret;
//
//    @Value("${app.jwt-expiration-milliseconds}")
//    private long jwtExpirationMs;
//
//    @Value("${app.jwt-refresh-expiration-milliseconds}")
//    private long jwtRefreshExpirationMs;
//
//    public String generateAccessToken(Authentication authentication) {
//        return generateToken(authentication, jwtExpirationMs);
//    }
//
//    public String generateRefreshToken(Authentication authentication) {
//        return generateToken(authentication, jwtRefreshExpirationMs);
//    }
//
//    private String generateToken(Authentication authentication, long expirationMs) {
//        String username = authentication.getName();
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + expirationMs);
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//    }
//
//    public String getUsernameFromToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//            return true;
//        } catch (Exception ex) {
//            // Handle specific exceptions as needed
//            return false;
//        }
//    }
//}