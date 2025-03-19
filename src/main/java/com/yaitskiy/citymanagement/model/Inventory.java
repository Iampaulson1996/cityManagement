package com.yaitskiy.citymanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Setter
@Getter
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue
    @Column(name = "item_id", updatable = false, nullable = false)
    private UUID itemId;

    @NotBlank(message = "Название обязательно")
    @Size(max = 100, message = "Максимальная длина 100 символов")
    private String name;

    @Size(max = 255, message = "Максимальная длина 255 символов")
    private String description;

    @Min(value = 0, message = "Количество не может быть отрицательным")
    private int quantity;

    @DecimalMin(value = "0.0", inclusive = true, message = "Цена не может быть отрицательной")
    private BigDecimal price;


}
