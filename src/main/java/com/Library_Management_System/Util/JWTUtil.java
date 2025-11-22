package com.Library_Management_System.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {

	private static final String SECRET = "Have_a_break_Have_a_kitkat_Add_by_Diary_Milk";
	private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) 
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

	public String extractEmail(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token).getSubject();
	}

	private Claims extractClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	public boolean validateToken(String userName, UserDetails userDetails, String token) {
		// TODO Auto-generated method stub
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token)
				.getExpiration()
				.before(new Date());
	}
}