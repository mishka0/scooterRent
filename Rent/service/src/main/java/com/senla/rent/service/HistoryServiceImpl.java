package com.senla.rent.service;

import com.senla.rent.api.dao.HistoryRepository;
import com.senla.rent.api.dto.history.HistoryWithScooterDTO;
import com.senla.rent.api.dto.history.HistoryWithUserDTO;
import com.senla.rent.api.service.HistoryService;
import com.senla.rent.entity.History;
import com.senla.rent.service.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final ModelMapper modelMapper;

    public HistoryServiceImpl(HistoryRepository historyRepository, ModelMapper modelMapper) {
        this.historyRepository = historyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<HistoryWithScooterDTO> getUserHistory(Integer id) {
        try {
            return historyRepository.getUserHistory(id)
                    .stream()
                    .map(history -> modelMapper.map(history, HistoryWithScooterDTO.class))
                    .collect(Collectors.toList());
        } catch (RuntimeException exception) {
            log.error("Can't get user history! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get user history!");
        }
    }

    @Override
    public List<HistoryWithUserDTO> getScooterHistory(Integer id) {
        try {
            return historyRepository.getScooterHistory(id)
                    .stream()
                    .map(history -> modelMapper.map(history, HistoryWithUserDTO.class))
                    .collect(Collectors.toList());
        } catch (RuntimeException exception) {
            log.error("Can't get scooter history! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get scooter history!");
        }
    }

    @Override
    public void addHistory(History history) {
        try {
            historyRepository.insert(history);
        } catch (RuntimeException exception) {
            log.error("Can't add history! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't add history!");
        }
    }

    @Override
    public boolean existNonClosed(Integer idUser) {
        try {
            return historyRepository.existNonClosed(idUser);
        } catch (RuntimeException exception) {
            log.error("Can't check non closed history! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't check non closed history!");
        }
    }

    @Override
    public History getNonClosedRent(Integer idUser) {
        try {
            return historyRepository.getNonClosedRent(idUser);
        } catch (RuntimeException exception) {
            log.error("Can't get non closed history! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get non closed history!");
        }
    }
}
