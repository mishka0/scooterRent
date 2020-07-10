package com.senla.rent.api.service;

import com.senla.rent.entity.Role;


public interface RoleService {
    Role findByName(String name);
}
