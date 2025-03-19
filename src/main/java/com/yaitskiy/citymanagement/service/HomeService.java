package com.yaitskiy.citymanagement.service;

import com.yaitskiy.citymanagement.exeption.EntityNotFoundException;
import com.yaitskiy.citymanagement.model.Home;
import com.yaitskiy.citymanagement.repository.HomeRepository;
import com.yaitskiy.citymanagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@AllArgsConstructor
public class HomeService {

    @Autowired
    @Lazy
    private PersonService personService;
    private HomeRepository homeRepository;
    private PersonRepository personRepository;
    @Autowired
    private CarService carService;


    public HomeService(HomeRepository homeRepository, PersonRepository personRepository) {
        this.homeRepository = homeRepository;
        this.personRepository = personRepository;
    }

    public Home save(Home home) {
        return homeRepository.save(home);
    }

    public void deleteById(Long id) {
        Home home = homeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Home", id));
        homeRepository.delete(home);
    }

    public List<Home> findAll() {
        if (homeRepository.count() != 0) {
            return homeRepository.findAll();
        }
        return null;
    }

    public Home findById(Long id) {
        if (homeRepository.existsById(id)) {
            return homeRepository.findById(id).orElseThrow();
        }
        return null;
    }



}
