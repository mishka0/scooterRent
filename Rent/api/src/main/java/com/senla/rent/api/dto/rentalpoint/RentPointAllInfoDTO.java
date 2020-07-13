package com.senla.rent.api.dto.rentalpoint;

import com.senla.rent.api.dto.town.TownDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RentPointAllInfoDTO {
    private Integer id;

    private String address;

    private TownDTO town;
}
