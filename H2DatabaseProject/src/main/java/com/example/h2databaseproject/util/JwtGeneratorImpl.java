package com.example.h2databaseproject.util;

import com.example.h2databaseproject.entities.Person;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtGeneratorImpl implements JwtGeneratorInterface{

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Map<String, String> generateToken(Person person) {
        String jwtToken="";
        jwtToken = Jwts.builder().setSubject(person.getEmail()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secret").compact();
        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("token", jwtToken);
        return jwtTokenGen;
    }
}
