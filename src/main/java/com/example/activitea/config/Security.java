package com.example.activitea.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.activitea.config.jwt.AuthEntryPointJwt;
import com.example.activitea.config.jwt.AuthTokenFilter;
import com.example.activitea.service.UserLoginDetailsService;



@Configuration 
@EnableMethodSecurity
public class Security {
	  @Autowired
	  private AuthEntryPointJwt unauthorizedHandler;


	@Bean
    public UserDetailsService userDetailsService() {
        return new UserLoginDetailsService();
    }


	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(passwordEncoder()); 
	    return authProvider;
	}
	
	  @Bean
	  public AuthTokenFilter authenticationJwtTokenFilter() {
	    return new AuthTokenFilter();
	  }
	  
	  @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	  }

 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
			    http.csrf(csrf -> csrf.disable())
			        .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
			        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			        .authorizeHttpRequests(auth -> 
			          auth.requestMatchers("/**").permitAll()
			          .requestMatchers(HttpMethod.POST,"/register").permitAll()
			          .requestMatchers(HttpMethod.POST,"/login").permitAll()
			              .anyRequest().authenticated()
			        );
			    
			   // http.authenticationProvider(authenticationJwtTokenFilter());

			    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
			    
			    return http.build();
	}
}
