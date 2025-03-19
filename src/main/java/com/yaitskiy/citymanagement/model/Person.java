package com.yaitskiy.citymanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Passport passport;
    @JsonIgnore
    @OneToMany
    @JoinTable(
            name = "person_car_list",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "car_list_id")
    )
    private List<Car> carList;
    @JsonIgnore
    @OneToMany
    @JoinTable(
            name = "person_home_list",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "home_list_id")
    )
    private List<Home> homeList;

    @PreRemove
    private void preRemove() {
        if (carList != null) {
            for (Car car : carList) {
                car.setOwner(null);
            }
        }
        if (homeList != null) {
            for (Home home : homeList) {
                home.setPerson(null);
            }
        }
    }



}
