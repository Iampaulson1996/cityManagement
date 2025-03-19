package com.yaitskiy.citymanagement.mapper;

import com.yaitskiy.citymanagement.dto.CarDTO;
import com.yaitskiy.citymanagement.dto.CarResponseDTO;
import com.yaitskiy.citymanagement.model.Car;
import com.yaitskiy.citymanagement.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    Car mapToEntity(CarDTO carDTO);

    @Mapping(target = "ownerId", source = "owner.id")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "brand", source = "brand")
    CarResponseDTO mapToDTO(Car car);

    @Mapping(target = "owner", source = "owner")
    Car mapToEntity(CarDTO carDTO, Person owner);


    List <CarResponseDTO> mapToResponseListDTO(List<Car> cars);


}
