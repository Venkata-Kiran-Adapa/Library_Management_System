package com.Library_Management_System.Filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Library_Management_System.Service.CustomUserDetailsService;
import com.Library_Management_System.Util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

	@Autowired
	JWTUtil jwtUtil;
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	    String authHeader=request.getHeader("Authorization");
	    String token=null;
	    String userName=null;
	    try {
		    if(authHeader!=null && authHeader.startsWith("Bearer ")) {
		    	token=authHeader.substring(7);
		    	userName=jwtUtil.extractEmail(token);	    		
		    }
		    if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
		    	UserDetails userDetails=customUserDetailsService.loadUserByUsername(userName);
		    	if(jwtUtil.validateToken(userName,userDetails,token)) {
		    		UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		    		SecurityContextHolder.getContext().setAuthentication(authToken);
		    		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		    	}
		    }
	    }catch (io.jsonwebtoken.ExpiredJwtException e) {
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.getWriter().write("Token expired");
	        return;
	    } catch (io.jsonwebtoken.MalformedJwtException e) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        response.getWriter().write("Invalid token format ");
	        response.getWriter().write("Invalid token signature");
	        return;
	    }

	    filterChain.doFilter(request, response);
	}
	

}
