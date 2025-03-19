package com.yaitskiy.citymanagement.controller;

import com.yaitskiy.citymanagement.dto.PersonDTO;
import com.yaitskiy.citymanagement.dto.PersonResponseDTO;
import com.yaitskiy.citymanagement.mapper.PersonMapper;
import com.yaitskiy.citymanagement.model.Person;
import com.yaitskiy.citymanagement.service.PersonService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonMapper personMapper;

    @PostMapping("/create")
    public PersonResponseDTO createPerson(@RequestBody PersonDTO personDTO) {
        Person person = personMapper.mapToEntity(personDTO);
        Person savingPerson = personService.save(person);
        PersonResponseDTO personResponseDTO = personMapper.mapToDTO(savingPerson);
        return personResponseDTO;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.findAll();
        return ResponseEntity.ok(persons);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePersonById(@RequestParam("id") Long id) {
        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Person> getPersonById(@RequestParam("id") Long id) {
        Person person = personService.findById(id);
        return ResponseEntity.ok(person);
    }
}
