package com.example.h2databaseproject.services;
import com.example.h2databaseproject.dtos.PersonDTO;
import com.example.h2databaseproject.entities.Person;
import com.example.h2databaseproject.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {
    void savePerson(List<PersonDTO> dto);
    void updatePerson(List<PersonDTO> dto);
    void deletePerson(List<PersonDTO> dto);
    void deleteAllPersons();
    Long countPersons();
    List<Person> getAllPersons();
    Person getOnePerson(Long id);
    List<Person> findPersonsByFirstName(String keyword);
    List<Person> findPersonsByLastName(String keyword);
    List<Person> findPersonsByDesiredAge(int age);
    List<Person> findPersonsByYoungerThenDesiredAge(int age);
    List<Person> findPersonsByOlderThenDesiredAge(int age);
    List<Person> sortAscPersonsByAge();
    List<Person> sortDescPersonsByAge();
    Person getPersonByEmail(String email);

    /*
    public Person getPersonByEmailAndPassword(String email, String password) throws UserNotFoundException;
    boolean isPasswordMatch(Person person, String password);
    */
}
