package com.senla.rent.api.dto.user;

import com.senla.rent.api.dto.addition.AdditionDTO;
import com.senla.rent.api.dto.subscription.SubscriptionDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserFullInfoDTO {

    private String login;

    private AdditionDTO addition;

    private Set<SubscriptionDTO> subscriptions;
}
