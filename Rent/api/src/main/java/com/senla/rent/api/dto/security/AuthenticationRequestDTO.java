package com.senla.rent.api.dto.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequestDTO {

    private String login;

    private String password;
}
