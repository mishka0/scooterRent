package com.senla.rent.controller;

import com.senla.rent.api.dto.history.HistoryDTO;
import com.senla.rent.api.service.HistoryService;
import com.senla.rent.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/me/history")
public class UserHistoryController {

    private final HistoryService historyService;

    private final UserService userService;

    public UserHistoryController(HistoryService historyService, UserService userService) {
        this.historyService = historyService;
        this.userService = userService;
    }

    @GetMapping
    public List<HistoryDTO> getUserHistory(@RequestHeader(value = "Authorization") String token) {
        return historyService.getUserHistory(token);
    }

}
