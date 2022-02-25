package com.kasta.demo.security.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kasta.demo.security.AuthenticationRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class VOneFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper= new ObjectMapper( ) ;
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !(request.getServletPath().equals("/login/v1") && request.getMethod().equals("POST"));
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        AuthenticationRequest authenticationRequest= objectMapper.readValue(request.getInputStream(),AuthenticationRequest.class);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword(),new ArrayList<>()));
    filterChain.doFilter(request,response);

    }
}
