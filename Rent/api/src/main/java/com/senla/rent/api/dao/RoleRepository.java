package com.senla.rent.api.dao;


import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.entity.Role;

public interface RoleRepository extends GenericRepository<Role, Integer> {
    Role findByName(String role);
}
