package com.senla.rent.api.service;

import com.senla.rent.api.dto.history.HistoryWithScooterDTO;
import com.senla.rent.api.dto.history.HistoryWithUserDTO;
import com.senla.rent.entity.History;


import java.util.List;

public interface HistoryService {
    List<HistoryWithScooterDTO> getUserHistory(Integer id);

    List<HistoryWithUserDTO> getScooterHistory(Integer id);

    void addHistory(History history);

    boolean existNonClosed(Integer idUser);

    History getNonClosedRent(Integer idUser);
}
