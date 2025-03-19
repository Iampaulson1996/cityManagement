package com.yaitskiy.citymanagement.repository;

import com.yaitskiy.citymanagement.model.Passport;
import com.yaitskiy.citymanagement.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Long> {
}
