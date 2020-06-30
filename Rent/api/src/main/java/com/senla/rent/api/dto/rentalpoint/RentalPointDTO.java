package com.senla.rent.api.dto.rentalpoint;

import com.senla.rent.api.dto.scooter.ScooterDTO;
import com.senla.rent.api.dto.town.TownDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RentalPointDTO {

    private Integer id;

    private String address;
}
