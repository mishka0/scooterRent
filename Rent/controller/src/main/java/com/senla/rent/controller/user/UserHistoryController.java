package com.senla.rent.controller.user;

import com.senla.rent.api.dto.history.HistoryWithScooterDTO;
import com.senla.rent.api.dto.user.UserJWT;
import com.senla.rent.api.service.HistoryService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profile/history")
public class UserHistoryController {
    private final HistoryService historyService;

    public UserHistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping
    public List<HistoryWithScooterDTO> getUserHistory(@AuthenticationPrincipal UserJWT userJWT) {
        return historyService.getUserHistory(userJWT.getUser().getId());
    }
}
