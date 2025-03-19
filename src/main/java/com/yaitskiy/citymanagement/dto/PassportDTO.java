package com.yaitskiy.citymanagement.dto;

import com.yaitskiy.citymanagement.model.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassportDTO {
    private String series;
    private String number;
    private Long ownerId;
    private Person owner;
}
