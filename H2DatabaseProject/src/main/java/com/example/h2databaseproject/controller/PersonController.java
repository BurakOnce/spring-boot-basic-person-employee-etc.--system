package com.example.h2databaseproject.controller;

        import com.example.h2databaseproject.dtos.PersonDTO;
        import com.example.h2databaseproject.entities.Person;
        import com.example.h2databaseproject.services.PersonServiceImpl;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import java.util.List;

@RestController
public class PersonController {
    private PersonServiceImpl personService;


    @Autowired
    public void setPersonService(PersonServiceImpl personService) {
        this.personService = personService;
    }


    @PostMapping("/PostPerson")
    ResponseEntity<String> savePerson(@RequestBody List<PersonDTO> dtos) {personService.savePerson(dtos);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("saved all persons.");}

    @PostMapping("/UpdatePerson")
    void updatePerson(@RequestBody List<PersonDTO> dtos) {personService.updatePerson(dtos);}

    @DeleteMapping("/DeletePerson")
    void deletePerson(@RequestBody List<PersonDTO> dtos) { personService.deletePerson(dtos);}

    @DeleteMapping   ("/DeleteAllPersons")
    ResponseEntity<String> deleteAllPersons() { personService.deleteAllPersons();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("deleted all persons.");}

    @GetMapping   ("/CountPersons")
    Long countPersons() {return personService.countPersons();}

    @GetMapping   ("/GetAllPersons")
    List<Person> getAllPersons() {return personService.getAllPersons();}

    @GetMapping   ("/GetOnePerson")
    Person getOnePerson(@RequestParam Long id) {return personService.getOnePerson(id);}

    @GetMapping   ("/FindPersonsByFirstName")
    List<Person> findPersonsByFirstName(String firstName){return personService.findPersonsByFirstName(firstName);};

    @GetMapping   ("/FindPersonsByLastName")
    List<Person> findPersonsByLastName(String lastName){return personService.findPersonsByLastName(lastName);};

    @GetMapping   ("/FindPersonsByDesiredAge")
    List<Person> findPersonsByDesiredAge(int age){return personService.findPersonsByDesiredAge(age);};

    @GetMapping   ("/FindPersonsByYoungerThenDesiredAge")
    List<Person> findPersonsByYoungerThenDesiredAge(int age){return personService.findPersonsByYoungerThenDesiredAge(age);};

    @GetMapping   ("/FindPersonsByOlderThenDesiredAge")
    List<Person> findPersonsByOlderThenDesiredAge(int age){return personService.findPersonsByOlderThenDesiredAge(age);};

    @GetMapping   ("/SortAscPersonsByAge")
    List<Person> sortAscPersonsByAge(){return personService.sortAscPersonsByAge();};

    @GetMapping   ("/SortDescPersonsByAge")
    List<Person> sortDescPersonsByAge(){return personService.sortDescPersonsByAge();};



    }

