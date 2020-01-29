package com.company.retrospective.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	
	@Value("${jwt.secret}")
	private String secret;
	
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	
	private Claims getAllClaimsFromToken(String token) {
		System.out.println("JwtTokenUtil -> getAllClaimsFromToken");

		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
		System.out.println("JwtTokenUtil -> getClaimsFromToken");

		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	public String getUsernameFromToken(String token) {
		System.out.println("JwtTokenUtil -> getUsernameFromToken");

		return getClaimsFromToken(token, Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token) {
		System.out.println("JwtTokenUtil -> getExpirationDateFromToken");

		return getClaimsFromToken(token, Claims::getExpiration);
	}	
	
	private boolean isTokenExpired(String token) {
		System.out.println("JwtTokenUtil -> isTokenExpired");

		final Date expirationDate = getExpirationDateFromToken(token);
		return expirationDate.before(new Date());
	}
	
	public String generateToken(UserDetails userDetails) {
		System.out.println("JwtTokenUtil -> generateToken");

		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims,userDetails.getUsername());
		
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		System.out.println("JwtTokenUtil -> doGenerateToken");

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
				  .signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	public boolean validateToken(String token,UserDetails userDetaild) {
		System.out.println("JwtTokenUtil -> validateToken");

		final String userName = getUsernameFromToken(token);
		return (userName.equals(userDetaild.getUsername())&& !isTokenExpired(token));
		
	}
}
