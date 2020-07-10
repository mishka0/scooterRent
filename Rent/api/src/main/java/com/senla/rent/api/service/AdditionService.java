package com.senla.rent.api.service;

import com.senla.rent.api.dto.addition.AdditionDTO;
import com.senla.rent.api.dto.addition.AdditionEditDTO;

public interface AdditionService {
    AdditionDTO getUserAddition(Integer id);

    void updateAddition(Integer id, AdditionEditDTO additionEditDTO);
}
