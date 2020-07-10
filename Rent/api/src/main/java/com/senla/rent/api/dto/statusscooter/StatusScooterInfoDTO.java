package com.senla.rent.api.dto.statusscooter;

import com.senla.rent.api.dto.scooter.ScooterDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class StatusScooterInfoDTO {
    private Integer id;

    private String status;

    private Set<ScooterDTO> scooters;
}
