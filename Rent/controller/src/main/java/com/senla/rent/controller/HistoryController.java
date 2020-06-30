package com.senla.rent.controller;


import com.senla.rent.api.dto.history.HistoryDTO;
import com.senla.rent.api.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/admin")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("/history")
    public Set<HistoryDTO> getAllHistories(){
//        historyService.get
        return null;
    }
}
