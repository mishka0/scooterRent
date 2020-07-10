package com.senla.rent.service;

import com.senla.rent.api.dao.RoleRepository;
import com.senla.rent.api.service.RoleService;
import com.senla.rent.entity.Role;
import com.senla.rent.service.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        try {
            return roleRepository.findByName(name);
        } catch (RuntimeException exception) {
            log.error("Can't get role by name! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get role by name!");
        }
    }
}
