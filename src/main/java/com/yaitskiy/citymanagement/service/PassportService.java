package com.yaitskiy.citymanagement.service;

import com.yaitskiy.citymanagement.exeption.EntityNotFoundException;
import com.yaitskiy.citymanagement.model.Car;
import com.yaitskiy.citymanagement.model.Passport;
import com.yaitskiy.citymanagement.model.Person;
import com.yaitskiy.citymanagement.repository.CarRepository;
import com.yaitskiy.citymanagement.repository.PassportRepository;
import com.yaitskiy.citymanagement.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor

public class PassportService {

    private PassportRepository passportRepository;
    private PersonRepository personRepository;

    public void assignToPerson(Passport passport, Long ownerId, String series, String number) {
        Person owner = personRepository.findById(ownerId).orElse(null);
        if (owner == null) {
            throw new IllegalArgumentException("Person with " + ownerId + " id not found");
        }

        passport.setOwner(owner);
        passport.setSeries(series);
        passport.setNumber(number);
        passportRepository.save(passport);
    }

    public Passport save(Passport passport) {
        return passportRepository.save(passport);
    }

    public Passport save() {
        var passport =  new Passport();

        return passportRepository.save(passport);
    }
    public Passport save(Person person) {
        var passport =  new Passport();
        Random random = new Random();

        passport.setNumber(String.valueOf(random.nextInt(8000) + 1000));
        passport.setSeries(String.valueOf(random.nextInt(800000) + 100000));
        passport.setOwner(person);
        return passportRepository.save(passport);
    }

    public void deleteById(Long id) {
        Passport passport = passportRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Passport", id));
        passportRepository.delete(passport);
    }

    public List<Passport> findAll() {
        if (passportRepository.count() != 0) {
            return passportRepository.findAll();
        }
        return null;
    }
    public Passport findById(Long id) {
        return passportRepository.findById(id).orElseThrow();
    }
}
