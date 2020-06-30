package com.senla.rent.service;

import com.senla.rent.api.dao.AdditionRepository;
import com.senla.rent.api.dto.addition.AdditionDTO;
import com.senla.rent.api.security.JwtTokenProvider;
import com.senla.rent.api.service.AdditionService;
import com.senla.rent.api.service.UserService;
import com.senla.rent.entity.Addition;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AdditionServiceImpl implements AdditionService {

    private final AdditionRepository additionRepository;

    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    private final ModelMapper modelMapper;

    public AdditionServiceImpl(AdditionRepository additionRepository, UserService userService, JwtTokenProvider jwtTokenProvider, ModelMapper modelMapper) {
        this.additionRepository = additionRepository;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.modelMapper = modelMapper;
    }

    @Override
    public AdditionDTO getUserAddition(String token) {

        try {
            return modelMapper.map(additionRepository
                    .getUserAddition(userService
                            .getUserId(jwtTokenProvider.getUsername(token))), AdditionDTO.class);
        }
        catch (RuntimeException exception)
        {
            log.error("Can't get user addition: " + exception.getMessage());
        }
        return new AdditionDTO();
    }

    @Override
    public void updateAddition(String token, AdditionDTO additionDTO) {

        Addition additionOld = additionRepository.findById(userService.getUserId(jwtTokenProvider.getUsername(token)));
        modelMapper.map(additionDTO, additionOld);
        additionRepository.update(additionOld);
    }

    @Override
    public void insertAddition(AdditionDTO additionDTO) {
        additionRepository.insert(modelMapper.map(additionDTO, Addition.class));
    }
}
