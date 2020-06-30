package com.senla.rent.api.dto.scooter;

import com.senla.rent.entity.StatusScooter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScooterInfoDTO {

    private Integer id;

    private String model;

    private StatusScooterDTO statusScooter;
}
