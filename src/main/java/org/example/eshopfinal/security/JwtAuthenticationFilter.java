package org.example.eshopfinal.security;


import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author mhmdz
 * Created By Zeeshan on 20-05-2023
 * @project oauth-jwt
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtilities jwtUtilities ;
    private final CustomerUserDetailsService customerUserDetailsService ;

   @Override
   protected void doFilterInternal(@NonNull HttpServletRequest request,
                                   @NonNull HttpServletResponse response,
                                   @NonNull FilterChain filterChain)
           throws ServletException, IOException {
       String token = jwtUtilities.getToken(request) ;
       if (token!=null && jwtUtilities.validateToken(token))
       {
           String email = jwtUtilities.extractUsername(token);
           UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);
           if (userDetails != null) {
               UsernamePasswordAuthenticationToken authentication =
                       new UsernamePasswordAuthenticationToken(userDetails.
                               getUsername() ,null , userDetails.getAuthorities());
               log.info("authenticated user with email :{}", email);
               SecurityContextHolder.getContext().setAuthentication(authentication);
           }
       }
       filterChain.doFilter(request,response);
   }
}

