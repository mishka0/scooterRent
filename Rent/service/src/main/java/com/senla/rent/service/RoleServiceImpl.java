package com.senla.rent.service;

import com.senla.rent.api.dao.RoleRepository;
import com.senla.rent.api.dto.user.RoleDTO;
import com.senla.rent.api.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RoleServiceImpl implements RoleService{


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoleDTO findByName(String name) {
        return modelMapper.map(roleRepository.findByName(name), RoleDTO.class);
    }

}
