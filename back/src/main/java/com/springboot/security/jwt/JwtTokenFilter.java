package com.springboot.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.security.service.UserDetailsServiceImpl;

public class JwtTokenFilter extends OncePerRequestFilter { //se ejecuta por cada petición, comprueba si es válido el token
	
	private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filter)
			throws ServletException, IOException {
		
		try {
			String token = getToken(req);
			
			 if (token != null && jwtProvider.validateToken(token)) {
				 String userName = jwtProvider.getUserNameFromToken(token);
				 UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
				 UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				 SecurityContextHolder.getContext().setAuthentication(auth);
			 }
		} catch (Exception e) {
			logger.error("Falló en el método doFilter " + e.getMessage());
		}
		
		filter.doFilter(req, res);
		
	}
	
	private String getToken(HttpServletRequest req) {
		String header = req.getHeader("Authorization");
		
		if (header != null && header.startsWith("Bearer"))
			return header.replace("Bearer ", "");
		return null;
		
	}
	
	

}
