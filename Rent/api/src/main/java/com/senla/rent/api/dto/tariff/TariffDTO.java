package com.senla.rent.api.dto.tariff;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TariffDTO {

    private Integer id;

    private String name;

    private Double cost;

    private String durationTariff;
}
