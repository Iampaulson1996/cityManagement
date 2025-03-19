package com.yaitskiy.citymanagement.mapper;

import com.yaitskiy.citymanagement.dto.PassportDTO;
import com.yaitskiy.citymanagement.dto.PassportResponseDTO;
import com.yaitskiy.citymanagement.model.Car;
import com.yaitskiy.citymanagement.model.Passport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PassportMapper {
    Passport mapToEntity(PassportDTO passportDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "series", source = "series")
    @Mapping(target = "number", source = "number")
    PassportResponseDTO mapToDTO(Passport passport);

    @Mapping(target = "owner.id" , source = "ownerId")
    @Mapping(target = "owner.firstName" , source = "ownerFirstName")
    @Mapping(target = "owner.carList" , source = "cars")
    Passport mapToEntity(PassportDTO passportDTO, Long ownerId, String ownerFirstName, List<Car> cars);

}
