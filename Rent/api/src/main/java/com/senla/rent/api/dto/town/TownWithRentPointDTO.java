package com.senla.rent.api.dto.town;

import com.senla.rent.api.dto.rentalpoint.RentPointDTO;
import com.senla.rent.api.dto.rentalpoint.RentPointWithScooterDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TownWithRentPointDTO {

    private Integer id;

    private String name;

    private Set<RentPointWithScooterDTO> pointSet;
}
