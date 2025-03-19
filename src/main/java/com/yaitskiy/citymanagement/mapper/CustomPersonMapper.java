package com.yaitskiy.citymanagement.mapper;

import com.yaitskiy.citymanagement.dto.PersonDTO;
import com.yaitskiy.citymanagement.model.Person;

public class CustomPersonMapper {
    public static Person mapPerson(PersonDTO personDTO) {
        Person person1 = new Person();
        person1.setFirstName(personDTO.getFirstName());
        return person1;
    }
}
