package com.senla.rent.api.service;

import com.senla.rent.api.dto.user.RoleDTO;


public interface RoleService {
    RoleDTO findByName(String name);
}
