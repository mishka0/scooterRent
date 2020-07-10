package com.senla.rent.api.dto.tariff;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;

@Getter
@Setter
public class TariffDTO {

    private Integer id;

    private String name;

    private Double cost;

    private String durationTariff;
}
