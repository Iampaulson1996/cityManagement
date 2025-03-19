package com.yaitskiy.citymanagement.controller;

import com.yaitskiy.citymanagement.dto.CarDTO;
import com.yaitskiy.citymanagement.dto.CarResponseDTO;
import com.yaitskiy.citymanagement.mapper.CarMapper;
import com.yaitskiy.citymanagement.model.Car;
import com.yaitskiy.citymanagement.service.CarService;
import com.yaitskiy.citymanagement.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService carService;
    private final PersonService personService;
    private final CarMapper carMapper;

    // Создание автомобиля – делегирует установку владельца в PersonService
    @PostMapping("/create")
    public ResponseEntity<CarResponseDTO> createCar(@RequestBody CarDTO carDTO) {
        if (carDTO.getOwnerId() == null) {
            throw new IllegalArgumentException("Owner ID must not be null");
        }
        Car savedCar = personService.addCarToPerson(carDTO.getOwnerId(), carMapper.mapToEntity(carDTO));
        return ResponseEntity.ok(carMapper.mapToDTO(savedCar));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CarResponseDTO>> getAllCars() {
        List<CarResponseDTO> dtos = carMapper.mapToResponseListDTO(carService.findAll());
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable("id") Long id) {
        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDTO> getCarById(@PathVariable("id") Long id) {
        Car car = carService.findById(id);
        return ResponseEntity.ok(carMapper.mapToDTO(car));
    }

    @GetMapping("/byOwner/{ownerId}")
    public ResponseEntity<List<CarResponseDTO>> getCarsByOwnerId(@PathVariable("ownerId") Long ownerId) {
        List<Car> cars = carService.findByOwnerId(ownerId);
        return ResponseEntity.ok(carMapper.mapToResponseListDTO(cars));
    }
}
