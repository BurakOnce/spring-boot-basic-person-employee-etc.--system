package com.example.h2databaseproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String password;

}
