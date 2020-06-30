package com.senla.rent.controller;


import com.senla.rent.api.dto.addition.AdditionDTO;
import com.senla.rent.api.service.AdditionService;
import com.senla.rent.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me/addition")
public class UserAdditionController {

    private final AdditionService additionService;

    public UserAdditionController(AdditionService additionService) {
        this.additionService = additionService;
    }

    @GetMapping
    public AdditionDTO getUserAddition(@RequestHeader(value = "Authorization") String token) {
        return additionService.getUserAddition(token);
    }

    @PutMapping
    public ResponseEntity<AdditionDTO> editUserInfo(@RequestHeader(value = "Authorization") String token, @RequestBody AdditionDTO additionDTO) {
        additionService.updateAddition(token, additionDTO);
        return new ResponseEntity<>(additionDTO, HttpStatus.OK);
    }

}