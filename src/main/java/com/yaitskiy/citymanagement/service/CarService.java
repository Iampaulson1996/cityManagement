package com.yaitskiy.citymanagement.service;

import com.yaitskiy.citymanagement.exeption.EntityNotFoundException;
import com.yaitskiy.citymanagement.model.Car;
import com.yaitskiy.citymanagement.model.Person;
import com.yaitskiy.citymanagement.repository.CarRepository;
import com.yaitskiy.citymanagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@AllArgsConstructor
@Transactional
public class CarService {

    @Autowired
    @Lazy
    private PersonService personService;
    private CarRepository carRepository;
    private PersonRepository personRepository;

    public CarService(CarRepository carRepository, PersonRepository personRepository) {
        this.carRepository = carRepository;
        this.personRepository = personRepository;
    }

    public void assignToPerson(Car car, Long ownerId) {
        Person owner = personRepository.findById(ownerId).orElse(null);
        if (owner == null) {
            throw new IllegalArgumentException("Person with " + ownerId + " id not found");
        }

        car.setOwner(owner);
        carRepository.save(car);
    }


    public Car save(Car car) {
        return carRepository.save(car);
    }

    public void deleteById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Car", id));
        carRepository.delete(car);
    }

    public List<Car> findAll() {
        if (carRepository.count() != 0) {
            return carRepository.findAll();
        }
        return null;
    }


    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow();
    }

    public List<Car> findByOwnerId(Long ownerId) {
        if (carRepository.count() != 0) {
            return carRepository.findCarsByOwnerId(ownerId);
        }
        return null;
    }
}
