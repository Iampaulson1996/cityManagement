package com.yaitskiy.citymanagement.mapper;

import com.yaitskiy.citymanagement.dto.PersonDTO;
import com.yaitskiy.citymanagement.dto.PersonResponseDTO;
import com.yaitskiy.citymanagement.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person mapToEntity(PersonDTO personDTO);
    @Mapping(target = "name", source = "firstName")
    @Mapping(target = "id", source = "id")
    PersonResponseDTO mapToDTO(Person person);
}
