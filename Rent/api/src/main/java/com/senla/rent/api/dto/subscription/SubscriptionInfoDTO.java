package com.senla.rent.api.dto.subscription;

import com.senla.rent.entity.Subscription;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
public class SubscriptionInfoDTO {
    private Integer id;

    private String name;

    private Double cost;

    private String time;

}
