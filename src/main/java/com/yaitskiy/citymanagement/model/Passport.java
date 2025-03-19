package com.yaitskiy.citymanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
//@Builder
@AllArgsConstructor
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String series;
    private String number;
    @OneToOne()
    @JoinColumn(name = "owner_id",unique = true) // Внешний ключ для Person
    private Person owner;
}
