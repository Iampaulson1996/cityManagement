package com.yaitskiy.citymanagement.controller;

import com.yaitskiy.citymanagement.dto.HomeDTO;
import com.yaitskiy.citymanagement.dto.HomeResponseDTO;
import com.yaitskiy.citymanagement.mapper.HomeMapper;
import com.yaitskiy.citymanagement.model.Home;
import com.yaitskiy.citymanagement.service.HomeService;
import com.yaitskiy.citymanagement.service.PersonService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;
    @Autowired
    private PersonService personService;
    @Autowired
    private HomeMapper homeMapper;

    @PostMapping("/create")
    public HomeResponseDTO createCar(@RequestBody HomeDTO homeDTO) {
        if (homeDTO.getPersonId() == null) {
            throw new IllegalArgumentException("Owner ID must not be null");
        }

        var owner = personService.findById(homeDTO.getPersonId());



        Home home = homeMapper.mapToEntity(homeDTO, owner);

        owner.getHomeList().add(home);

        home.setId(null);
        Home savedHome = homeService.save(home);

        savedHome.setPerson(owner);

        return homeMapper.mapToDTO(savedHome);
    }


    @GetMapping("/all")
    public ResponseEntity<List<HomeResponseDTO>> getAllHomes() {
        List<HomeResponseDTO> homes = homeService.findAll()
                .stream()
                .map(homeMapper::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(homes);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteHomeById(@PathParam("id") Long id) {
        homeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public HomeResponseDTO getHomeById(@PathParam("id") Long id) {
        Home home = homeService.findById(id);
        return homeMapper.mapToDTO(home);
    }




}
