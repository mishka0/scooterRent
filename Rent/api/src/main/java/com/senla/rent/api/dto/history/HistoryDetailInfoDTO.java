package com.senla.rent.api.dto.history;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.rent.api.dto.scooter.ScooterDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class HistoryDetailInfoDTO {

    private Integer id;

    private Double cost;

    private String timeRide;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateRide;

    private ScooterDTO scooter;

    private boolean isSubscription;

    private String login;

    private String firstName;

    private String lastName;

    private String phone;

    private Integer discount;
}

