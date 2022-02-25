package com.kasta.demo.security.v1;

import com.kasta.demo.security.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login/v1")
public class VOneController {
    @PostMapping
    public ResponseEntity<UserDetails> authenticate (@AuthenticationPrincipal UserDetails userDetails){
        return new ResponseEntity<>(userDetails,HttpStatus.OK);
    }
}
