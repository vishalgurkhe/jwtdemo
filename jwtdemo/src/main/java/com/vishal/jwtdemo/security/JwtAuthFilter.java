package com.vishal.jwtdemo.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vishal.jwtdemo.models.User;
import com.vishal.jwtdemo.repos.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String requestTokenHeader =
                request.getHeader("Authorization");
        
        System.out.println("requestTokenHeader >>"+requestTokenHeader);

        if (requestTokenHeader == null ||
                !requestTokenHeader.startsWith("Bearer ")) {

        	System.out.println("Inside if  ");
            filterChain.doFilter(request, response);
            return;
        }

        String token = requestTokenHeader.substring(7);

        System.out.println("String token >>"+token);
        String userName = jwtUtil.getUserNameFromToken(token);

        System.out.println("String username >>"+userName);
        
        if (userName != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            User user = userRepo.findByUserName(userName)
                    .orElseThrow();

            System.out.println("User >>"+user.toString());
            
            UsernamePasswordAuthenticationToken userToken =
                    new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities()
                    );

            SecurityContextHolder.getContext()
                    .setAuthentication(userToken);
        }

        filterChain.doFilter(request, response);
    }
}