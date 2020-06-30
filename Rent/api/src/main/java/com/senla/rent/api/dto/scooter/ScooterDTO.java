package com.senla.rent.api.dto.scooter;

import com.senla.rent.entity.History;
import com.senla.rent.entity.StatusScooter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class ScooterDTO {

    private Integer id;

    private String model;
}
