package com.zest.technical.config;

import com.zest.technical.security.JWTAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    // CORS configuration and HTTPS enforcement.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers("/api/v1/auth/login", "/api/v1/auth/refresh-token").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/products").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/products/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v1/products/**").hasRole("USER")
                                .anyRequest().authenticated()
                )
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
