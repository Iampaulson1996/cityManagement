package com.yaitskiy.citymanagement.mapper;

import com.yaitskiy.citymanagement.dto.CarResponseDTO;
import com.yaitskiy.citymanagement.dto.HomeDTO;
import com.yaitskiy.citymanagement.dto.HomeResponseDTO;
import com.yaitskiy.citymanagement.model.Home;
import com.yaitskiy.citymanagement.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HomeMapper {
    Home mapToEntity (HomeDTO homeDTO);

    @Mapping(target = "personId", source = "person.id")
    @Mapping( target = "id", source = "id")
    @Mapping(target = "location", source = "location")
    HomeResponseDTO mapToDTO (Home home);

    @Mapping(target = "person", source = "person")
    Home mapToEntity (HomeDTO homeDTO, Person person);

}
