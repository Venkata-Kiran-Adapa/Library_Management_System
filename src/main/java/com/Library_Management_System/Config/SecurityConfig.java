package com.Library_Management_System.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Library_Management_System.Enums.Role;
import com.Library_Management_System.Filters.JWTAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JWTAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	  http.csrf(AbstractHttpConfigurer::disable)
          .authorizeHttpRequests(auth -> 
		    auth.requestMatchers("/api/authenticate/**").permitAll()
			    .requestMatchers(HttpMethod.GET, "/api/book/**").hasAnyRole(Role.ADMIN.name(), Role.LIBRARIAN.name(), Role.MEMBER.name())
			    .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole(Role.ADMIN.name())
			    .requestMatchers(HttpMethod.PATCH, "/api/book","/api/borrowRecords","/api/reservation").hasAnyRole(Role.ADMIN.name(), Role.LIBRARIAN.name())
			    .requestMatchers(HttpMethod.PATCH, "/api/user/**").hasRole(Role.ADMIN.name())
			    .requestMatchers(HttpMethod.POST, "/api/**").hasAnyRole(Role.ADMIN.name(), Role.LIBRARIAN.name())
			    .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole(Role.ADMIN.name(), Role.LIBRARIAN.name())
			    .anyRequest().authenticated()
);
    	http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
        
        
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @SuppressWarnings("deprecation")
	@Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder){
    	DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
    	daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    	return new ProviderManager(daoAuthenticationProvider);
    }
    	
    }

