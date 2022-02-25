package com.kasta.demo.security.v2;

import com.kasta.demo.security.AuthenticationRequest;
import com.kasta.demo.security.CustomUserService;
import com.kasta.demo.security.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.web.bind.annotation.*;

import javax.servlet.FilterChain;

@RestController
@RequestMapping("/login/v2")
public class VTwoController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserService customUserService;
    @PostMapping
    public ResponseEntity<UserDetails> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        try{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
        }catch (BadCredentialsException ex){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
return null;

    }
    @GetMapping("/test")
    public Object greeting (@AuthenticationPrincipal Object principal){
        return principal;
    }

}
