package com.yaitskiy.citymanagement.repository;

import com.yaitskiy.citymanagement.model.Home;
import com.yaitskiy.citymanagement.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeRepository extends JpaRepository<Home, Long> {
    List<Home> findByLocation(String location);
}
