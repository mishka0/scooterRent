package com.senla.rent.api.dto.history;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.rent.api.dto.scooter.ScooterDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class HistoryDTO {

    private Integer id;

    private Double cost;

    private String timeRide;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateRide;

    private ScooterDTO scooter;

    private boolean isSubscription;
}
