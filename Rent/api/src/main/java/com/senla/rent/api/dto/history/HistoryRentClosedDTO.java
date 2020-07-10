package com.senla.rent.api.dto.history;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.rent.api.dto.subscription.SubscriptionInfoDTO;
import com.senla.rent.api.dto.tariff.TariffDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class HistoryRentClosedDTO {
    private Integer id;

    private Double cost;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateRide;


    @JsonFormat(pattern = "HH:mm::ss")
    private LocalTime timeStart;

    @JsonFormat(pattern = "HH:mm::ss")
    private LocalTime timeEnd;

    private TariffDTO tariff;

    private SubscriptionInfoDTO subscriptionInfo;

    private boolean isClosed;
}
