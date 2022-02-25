package com.kasta.demo.security.v3;

import com.kasta.demo.security.AuthenticationRequest;
import com.kasta.demo.security.Person;
import com.kasta.demo.security.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login/v3")
public class VThreeController {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping
    public ResponseEntity<Person> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        Person person = this.personRepository.getByUsername(authenticationRequest.getUsername());
        if (passwordEncoder.matches(authenticationRequest.getPassword(),person.getPassword())){
            return new ResponseEntity<>(person, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
