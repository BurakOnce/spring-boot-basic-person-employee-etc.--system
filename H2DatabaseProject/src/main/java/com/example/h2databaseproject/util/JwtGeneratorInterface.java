package com.example.h2databaseproject.util;

import com.example.h2databaseproject.entities.Person;

import java.util.Map;

public interface JwtGeneratorInterface {
    Map<String, String> generateToken(Person person);
}
