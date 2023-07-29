package com.example.h2databaseproject.services;

import com.example.h2databaseproject.dtos.PersonDTO;
import com.example.h2databaseproject.entities.Person;
import com.example.h2databaseproject.exceptions.UserNotFoundException;
import com.example.h2databaseproject.repositories.PersonRepository;
import com.example.h2databaseproject.util.Helper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

import java.util.Optional;

@Service
//@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {


    private final PersonRepository repository;
    private final Helper helper;

    /*
    * with SecurityConfig (in util) we can use passwordEncoder
    * */
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void savePerson(List<PersonDTO> dtos) {
        for (PersonDTO dto:dtos){
            Person person = new Person();
            person.setFirstName(dto.getFirstName());
            person.setLastName(dto.getLastName());
            person.setAge(dto.getAge());
            person.setEmail(dto.getEmail());

            String encodedPassword = this.passwordEncoder.encode(dto.getPassword());
            person.setPassword(encodedPassword);

            repository.save(person);
        }
        System.out.println(helper.addSuccess()+repository.count()+" person");
    }
    @Override
    public void updatePerson(List<PersonDTO> dtos) {
        for (PersonDTO dto : dtos) {
            Optional<Person> optionalPerson = repository.findById(dto.getId());
            if (optionalPerson.isPresent()) {
                Person personToUpdate = optionalPerson.get();
                personToUpdate.setFirstName(dto.getFirstName());
                personToUpdate.setLastName(dto.getLastName());
                personToUpdate.setAge(dto.getAge());
                personToUpdate.setEmail(dto.getEmail());

                String encodedPassword = this.passwordEncoder.encode(dto.getPassword());
                personToUpdate.setPassword(encodedPassword);

                repository.save(personToUpdate);
                System.out.println(helper.updateSuccess() + "id: " + dto.getId());
            } else {
                System.out.println("not found person. id:" + dto.getId());
            }
        }
    }


    @Override
    public void deletePerson(List<PersonDTO> dtos) {
        for (PersonDTO dto : dtos) {
            //System.out.println(helper.deleteSuccess()+"Name: "+dto.getName()+"    Age: "+dto.getAge());

            Optional<Person> optionalPerson = repository.findById(dto.getId());

            if (optionalPerson.isPresent()) {
                System.out.println("name: "+optionalPerson.get().getFirstName()+" "+optionalPerson.get().getLastName());
                Person personToDelete = optionalPerson.get();
                repository.delete(personToDelete);
            } else {
                System.out.println("not found person. id:" + dto.getId());
            }
        }
    }
    @Override
    public Long countPersons() {
        return repository.count();
    }
    @Override
    public List<Person> getAllPersons() {
        return repository.findAll();
    }

    public void deleteAllPersons() {repository.deleteAll();}

    @Override
    public Person getOnePerson(Long id) {
        //return repository.getReferenceById(id);
        try{
            return repository.findById(id).get();
        }catch (Exception e){
            System.err.println(e);
            return null;
        }
    }
    @Override
    public List<Person> findPersonsByFirstName(String firstName) {

        if (firstName == null) {
            System.out.println("keyword is null");
            return null;
        }

        List<Person> allPersons = repository.findByFirstName(firstName);

        return allPersons;
    }
    @Override
    public List<Person> findPersonsByLastName(String lastName) {

        if (lastName == null) {
            System.out.println("keyword is null");
            return null;
        }

        List<Person> allPersons = repository.findByLastName(lastName);

        return allPersons;
    }
    @Override
    public List<Person> findPersonsByDesiredAge(int age){

        if (age <= 0) {
            System.out.println("age cant be negative");
            return null;
        }

        List<Person> allPersons= repository.findByAge(age);

        return allPersons;

    }

    @Override
    public List<Person> findPersonsByYoungerThenDesiredAge(int age){

        if (age <= 0) {
            System.out.println("age cant be negative");
            return null;
        }

        List<Person> allPersons= repository.findByAgeLessThan(age);

        return allPersons;
    }

    @Override
    public List<Person> findPersonsByOlderThenDesiredAge(int age){

        if (age <= 0) {
            System.out.println("age cant be negative");
            return null;
        }

        List<Person> allPersons= repository.findByAgeGreaterThan(age);

        return allPersons;
    }
    @Override
    public List<Person> sortAscPersonsByAge() {
        List<Person> sortedPersons = repository.findAll(Sort.by(Sort.Direction.ASC, "age"));
        return sortedPersons;
    }
    @Override
    public List<Person> sortDescPersonsByAge() {
        List<Person> sortedPersons = repository.findAll(Sort.by(Sort.Direction.DESC, "age"));
        return sortedPersons;
    }

    @Override
    public Person getPersonByEmail(String email) {
        return repository.findByEmail(email);
    }

    /*
    @Override
    public Person getPersonByEmailAndPassword(String email, String password) throws UserNotFoundException {
        String encodePassword = passwordEncoder.encode(password);
        Person person = repository.findByEmailAndPassword(email, encodePassword);
        if(person == null){
            throw new UserNotFoundException("Invalid id and password");
        }
        return person;
    }

    @Override
    public boolean isPasswordMatch(Person person, String password) {
        String encodedPassword = person.getPassword(); // veritabanındaki şifre (encoded)
        return passwordEncoder.matches(password, encodedPassword);
    }
    */

}




