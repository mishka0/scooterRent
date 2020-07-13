package com.senla.rent.api.dto.rentalpoint;

import com.senla.rent.api.dto.scooter.ScooterInfoDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RentPointWithScooterDTO {
    private Integer id;

    private String address;

    private Set<ScooterInfoDTO> scooters;
}
