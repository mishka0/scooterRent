package com.senla.rent.api.dto.subscription;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SubscriptionDTO {

    private Integer id;

    private String timeLeft;

    private SubscriptionInfoDTO subscriptionInfo;
}
