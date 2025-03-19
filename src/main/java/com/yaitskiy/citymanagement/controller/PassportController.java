package com.yaitskiy.citymanagement.controller;

import com.yaitskiy.citymanagement.dto.PassportDTO;
import com.yaitskiy.citymanagement.dto.PassportResponseDTO;
import com.yaitskiy.citymanagement.mapper.PassportMapper;
import com.yaitskiy.citymanagement.model.Passport;
import com.yaitskiy.citymanagement.service.PassportService;
import com.yaitskiy.citymanagement.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private final PassportService passportService;

    @Autowired
    private final PersonService personService;

    @Autowired
    private final PassportMapper passportMapper;

    @PostMapping("/create")
    public PassportResponseDTO createPassport(@RequestBody PassportDTO passportDTO) {
        if (passportDTO.getOwnerId() == null) {
            throw new IllegalArgumentException("Owner ID must not be null");
        }

        var owner = personService.findById(passportDTO.getOwnerId());
        //TODO Перенести работу с Pers Service в Passport Serv

        Passport passport = passportMapper.mapToEntity(passportDTO, owner.getId(), owner.getFirstName(),
                owner.getCarList());

        Passport savedPassport = passportService.save(passport);
        return passportMapper.mapToDTO(savedPassport);
    }


    @GetMapping("/all")
    public ResponseEntity<List<PassportResponseDTO>> getAllPassports() {
        List<PassportResponseDTO> passports = passportService.findAll()
                .stream()
                .map(passportMapper::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(passports);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassportById(@PathVariable("id") Long id) {
        passportService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public PassportResponseDTO getPassportById(@PathVariable("id") Long id) {
        Passport passport = passportService.findById(id);
        return passportMapper.mapToDTO(passport);
    }
}
