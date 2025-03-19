package com.yaitskiy.citymanagement.repository;

import com.yaitskiy.citymanagement.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findCarsByBrand(String brand);
    List<Car> findCarsByOwnerId(Long id);
}
