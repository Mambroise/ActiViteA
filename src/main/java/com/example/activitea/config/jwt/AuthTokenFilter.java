package com.example.activitea.config.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthTokenFilter extends OncePerRequestFilter{
	
	 @Autowired
	  private JwtUtils jwtUtils;

	  @Autowired
	  private UserDetailsService userDetailsService;
	  
	  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		   try {
			      String jwt = parseJwt(request);
			      System.err.println(jwt);
			      if(jwt == null || !jwt.equals("Login")){
			    	  if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
			    		  String username = jwtUtils.getUserNameFromJwtToken(jwt);
			    		  System.out.println("bearer ok");
			    		  UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			    		  UsernamePasswordAuthenticationToken authentication =
			    				  new UsernamePasswordAuthenticationToken(
			    						  userDetails,
			    						  null,
			    						  userDetails.getAuthorities());
			    		  authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			    		  
			    		  SecurityContextHolder.getContext().setAuthentication(authentication);
			    	  }else {
			    		  response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
			    		  return;
			    	  }
			      }
			    } catch (Exception e) {
			      logger.error("Cannot set user authentication: {}", e);
			    }
		   		
		   
			    filterChain.doFilter(request, response);
			  }

			  private String parseJwt(HttpServletRequest request) {
			    String headerAuth = request.getHeader("Authorization");
			    System.err.println(headerAuth);
			    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			    	System.err.println("ca rentre");
			      return headerAuth.substring(7);
			    }else if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Login")) {
			    	System.err.println("ca rentre dans login");
			      return headerAuth;
			    }
			    

			    return null;
			  
	}

}
