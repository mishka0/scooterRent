package com.senla.rent.api.dto.history;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.rent.api.dto.scooter.ScooterDTO;
import com.senla.rent.api.dto.tariff.TariffDTO;
import com.senla.rent.api.dto.user.UserWithAdditionDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class HistoryWithUserDTO {

    private Integer id;

    private Double cost;

    @JsonFormat(pattern = "HH:mm::ss")
    private LocalTime timeStart;

    @JsonFormat(pattern = "HH:mm::ss")
    private LocalTime timeEnd;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateRide;

    private ScooterDTO scooter;

    private TariffDTO tariff;

    private UserWithAdditionDTO user;

    private boolean isClosed;
}
