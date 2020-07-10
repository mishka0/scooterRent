package com.senla.rent.api.dto.user;

import com.senla.rent.api.dto.addition.AdditionDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithAdditionDTO {
    private Integer id;

    private String login;

    private AdditionDTO addition;
}
