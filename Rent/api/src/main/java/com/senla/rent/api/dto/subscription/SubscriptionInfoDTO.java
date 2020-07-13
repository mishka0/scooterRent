package com.senla.rent.api.dto.subscription;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionInfoDTO {
    private Integer id;

    private String name;

    private Double cost;

    private String time;
}
