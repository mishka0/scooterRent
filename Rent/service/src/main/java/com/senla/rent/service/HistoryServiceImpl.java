package com.senla.rent.service;

import com.senla.rent.api.dao.HistoryRepository;
import com.senla.rent.api.dto.history.HistoryDTO;
import com.senla.rent.api.security.JwtTokenProvider;
import com.senla.rent.api.service.HistoryService;
import com.senla.rent.api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public List<HistoryDTO> getUserHistory(String token) {
        return historyRepository.getUserHistory(
                userService.getUserId(jwtTokenProvider.getUsername(token)))
                .stream()
                .map(history -> modelMapper.map(history, HistoryDTO.class))
                .collect(Collectors.toList());
    }


}
