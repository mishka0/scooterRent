package com.senla.rent.api.dto.addition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdditionDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String phone;

    private Integer discount;

    private Double balance;
}
