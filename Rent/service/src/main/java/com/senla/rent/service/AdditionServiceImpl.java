package com.senla.rent.service;

import com.senla.rent.api.dao.AdditionRepository;
import com.senla.rent.api.dto.addition.AdditionDTO;
import com.senla.rent.api.dto.addition.AdditionEditDTO;
import com.senla.rent.api.security.JwtTokenProvider;
import com.senla.rent.api.service.AdditionService;
import com.senla.rent.api.service.UserService;
import com.senla.rent.entity.Addition;
import com.senla.rent.service.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AdditionServiceImpl implements AdditionService {

    private final AdditionRepository additionRepository;
    private final ModelMapper modelMapper;

    public AdditionServiceImpl(AdditionRepository additionRepository, ModelMapper modelMapper) {
        this.additionRepository = additionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AdditionDTO getUserAddition(Integer id) {
        try {
            return modelMapper.map(additionRepository.getUserAddition(id), AdditionDTO.class);
        }
        catch (RuntimeException exception)
        {
            log.error("Can't get user addition! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get user addition");
        }
    }

    @Override
    public void updateAddition(Integer id, AdditionEditDTO additionEditDTO) {

        try {
            Addition additionToUpdate = additionRepository.findById(id);
            modelMapper.map(additionEditDTO, additionToUpdate);
            additionRepository.update(additionToUpdate);
        } catch (RuntimeException exception) {
            log.error("Can't update addition! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't update user addition");
        }
    }
}
