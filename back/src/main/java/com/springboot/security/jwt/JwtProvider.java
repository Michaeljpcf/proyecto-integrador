package com.springboot.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.springboot.security.entity.User;
import com.springboot.security.entity.UserPrimary;
import com.springboot.security.repository.UserRepository;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider { //Genera el token, validacion que no este expirado

	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);	
		
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private int expiration;
	
	public String generateToken(Authentication authentication) {		
		UserPrimary userPrimary = (UserPrimary) authentication.getPrincipal();
		List<String> roles = userPrimary.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
//		Map<String, Object> params = Map.of("roles", roles, "idUser", userPrimary.getIdUser());
		Map<String, Object> params = new HashMap<>();
		params.put("id", userPrimary.getIdUser());
		params.put("name", userPrimary.getName());
		params.put("email", userPrimary.getEmail());
		params.put("roles", roles);
		return Jwts.builder()
				.setSubject(userPrimary.getUsername())
				.addClaims(params)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 1000))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
	public String getUserNameFromToken(String token) {
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("Token mal formado");
		} catch (UnsupportedJwtException e) {
			logger.error("Token no soportado");
		} catch (ExpiredJwtException e) {
			logger.error("Token expirado");
		} catch (IllegalArgumentException e) {
			logger.error("Token vacío");
		} catch (SignatureException e) {
			logger.error("Falló en la firma");
		}
		return false;
	}
}
