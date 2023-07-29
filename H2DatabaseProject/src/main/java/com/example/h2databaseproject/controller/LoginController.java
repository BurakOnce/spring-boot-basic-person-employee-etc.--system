package com.example.h2databaseproject.controller;
import com.example.h2databaseproject.dtos.PersonDTO;
import com.example.h2databaseproject.entities.Person;
import com.example.h2databaseproject.exceptions.UserNotFoundException;
import com.example.h2databaseproject.services.PersonService;
import com.example.h2databaseproject.util.JwtGeneratorInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
public class LoginController {

    @Autowired
    private PersonService personService;

    @Autowired
    private JwtGeneratorInterface jwtGenerator;

    @Autowired
    PasswordEncoder passwordEncoder;


        @PostMapping("/register")
        public ResponseEntity<?> postUser(@RequestBody PersonDTO person){
            List<PersonDTO> personList = new ArrayList<>();
            personList.add(person);
            try{
                personService.savePerson(personList);

                return new ResponseEntity<>(person, HttpStatus.CREATED);
            } catch (Exception e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
            }
        }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Person person) {
        try {
            if(person.getEmail() == null || person.getPassword() == null) {
                throw new UserNotFoundException("Email or Password is Empty");
            }
            Person personData = personService.getPersonByEmail(person.getEmail());
            if(personData == null || !passwordEncoder.matches(person.getPassword(), personData.getPassword())){
                throw new UserNotFoundException("Email or Password is Invalid");
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(person), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}