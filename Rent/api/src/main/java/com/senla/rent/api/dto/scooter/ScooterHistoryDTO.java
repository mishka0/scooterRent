package com.senla.rent.api.dto.scooter;

import com.senla.rent.api.dto.history.HistoryDTO;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Setter
@Getter
public class ScooterHistoryDTO {
    private Integer id;

    private String model;

    private Set<HistoryDTO> histories;
}
