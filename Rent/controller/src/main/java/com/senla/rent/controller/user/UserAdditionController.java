package com.senla.rent.controller.user;

import com.senla.rent.api.dto.addition.AdditionDTO;
import com.senla.rent.api.dto.addition.AdditionEditDTO;
import com.senla.rent.api.dto.user.UserJWT;
import com.senla.rent.api.service.AdditionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile/addition")
public class UserAdditionController {
    private final AdditionService additionService;

    public UserAdditionController(AdditionService additionService) {
        this.additionService = additionService;
    }

    @GetMapping
    public AdditionDTO getUserAddition(@AuthenticationPrincipal UserJWT userJWT) {
        return additionService.getUserAddition(userJWT.getUser().getId());
    }

    @PutMapping
    public ResponseEntity<AdditionEditDTO> editUserInfo(@AuthenticationPrincipal UserJWT userJWT, @RequestBody AdditionEditDTO additionEditDTO) {
        additionService.updateAddition(userJWT.getUser().getId(), additionEditDTO);
        return new ResponseEntity<>(additionEditDTO, HttpStatus.OK);
    }
}
