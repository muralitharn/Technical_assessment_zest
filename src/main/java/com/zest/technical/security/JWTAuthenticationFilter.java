package com.zest.technical.security;

import com.zest.technical.config.JWTConfig;
import io.jsonwebtoken.Claims;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.*;
import java.io.IOException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Collections;
import javax.crypto.SecretKey;

@WebFilter
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private static final SecretKey SECRET_KEY = JWTConfig.SECRET_KEY;
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        if (token == null || !token.startsWith(BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }
        token = token.substring(7); // Extract the JWT token from the "Bearer <token>" format

        try {
            // Validate the token and parse the claims
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            String role = claims.get("roles", String.class);  // Get role from claims

            if (username != null && role != null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, Collections.singletonList(new SimpleGrantedAuthority(role)));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (SignatureException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        filterChain.doFilter(request, response);
    }
}
