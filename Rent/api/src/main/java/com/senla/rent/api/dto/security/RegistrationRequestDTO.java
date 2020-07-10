package com.senla.rent.api.dto.security;

import com.senla.rent.api.dto.user.RoleDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegistrationRequestDTO {
    private String login;
    private String password;
}
