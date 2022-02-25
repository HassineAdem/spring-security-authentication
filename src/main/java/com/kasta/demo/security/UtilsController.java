package com.kasta.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UtilsController {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void build(){
        this.personRepository.save(Person.builder().username("foo").password(passwordEncoder.encode("foo")).build());
    }
}
