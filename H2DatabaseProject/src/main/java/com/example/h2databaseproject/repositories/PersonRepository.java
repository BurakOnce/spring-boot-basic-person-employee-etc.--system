package com.example.h2databaseproject.repositories;

import com.example.h2databaseproject.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.event.ListDataEvent;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

    List<Person> findByAge(int a);
    List<Person> findByAgeGreaterThan(int a);
    List<Person> findByAgeLessThan(int a);
    List<Person> findByFirstName(String firstName);
    List<Person> findByLastName(String lastName);
    Person findByEmail(String lastName);
    Person findByEmailAndPassword(String email, String password);

}
