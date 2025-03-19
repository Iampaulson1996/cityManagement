package com.yaitskiy.citymanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = true) // Внешний ключ для Person
    private Person owner;
    public void setOwner(Person owner) {
        this.owner = owner;
    }//Добавил, так как выходила ошибка


}
