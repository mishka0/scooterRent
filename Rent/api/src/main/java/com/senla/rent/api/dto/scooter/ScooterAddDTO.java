package com.senla.rent.api.dto.scooter;

import com.senla.rent.entity.StatusScooter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScooterAddDTO {

    private String model;

    private StatusScooter statusScooter;
}
