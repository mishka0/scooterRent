package com.senla.rent.api.dto.scooter;

import com.senla.rent.api.dto.rentalpoint.RentPointDTO;
import com.senla.rent.api.dto.statusscooter.StatusScooterDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScooterFullInfoDTO {

    private Integer id;

    private String model;

    private StatusScooterDTO statusScooter;

    private RentPointDTO rentPoint;

}
