package com.senla.rent.api.dto.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequestDTO {
    private String login;
    private String password;
}
