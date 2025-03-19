package com.yaitskiy.citymanagement.service;

import com.yaitskiy.citymanagement.exeption.EntityNotFoundException;
//import com.yaitskiy.citymanagement.exeption.PersonNotFoundException;
import com.yaitskiy.citymanagement.model.Car;
import com.yaitskiy.citymanagement.model.Passport;
import com.yaitskiy.citymanagement.model.Person;
import com.yaitskiy.citymanagement.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {
    private PersonRepository personRepository;
    private PassportService passportService;

    @Transactional
    public Person save(Person person) {
        Passport passport = passportService.save(person);
        person.setPassport(passport);
        return personRepository.save(person);
    }


    @Transactional
    public void deleteById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Person", id));
        personRepository.delete(person);
    }


    public List<Person> findAll() {
        if (personRepository.count() != 0) {
            return personRepository.findAll();
        }
        return null;
    }

    public boolean existsById(Long id) {
        return personRepository.existsById(id);
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Car addCarToPerson(Long ownerId, Car car) {
        Person owner = findById(ownerId);
        owner.getCarList().add(car);
        car.setOwner(owner);
        personRepository.save(owner);
        return car;
    }

}

