package com.senla.rent.api.service;

import com.senla.rent.api.dto.history.HistoryDTO;


import java.util.List;

public interface HistoryService {
    List<HistoryDTO> getUserHistory(String token);
}
