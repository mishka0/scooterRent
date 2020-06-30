package com.senla.rent.api.dto.town;

import com.senla.rent.api.dto.rentalpoint.RentalPointDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TownWithRentPointDTO {

    private Integer id;

    private String name;

    private Set<RentalPointDTO> pointSet;
}
