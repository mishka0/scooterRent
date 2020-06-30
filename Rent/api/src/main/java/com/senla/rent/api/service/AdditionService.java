package com.senla.rent.api.service;

import com.senla.rent.api.dto.addition.AdditionDTO;

public interface AdditionService {
    AdditionDTO getUserAddition(String token);

    void updateAddition(String token, AdditionDTO additionDTO);

    void insertAddition(AdditionDTO additionDTO);
}
