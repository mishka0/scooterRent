package com.senla.rent.controller;

import com.senla.rent.api.dto.projections.RentPointProj;
import com.senla.rent.api.dto.rentalpoint.RentPointWithTownDTO;
import com.senla.rent.api.service.springdata.RentPointSpringDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestServiceRentSpringDataController {

    @Autowired
    private RentPointSpringDataService rentPointSpringDataService;

    @GetMapping("/rent")
    public List<RentPointWithTownDTO> getAllTariffs(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit ){
        return rentPointSpringDataService.getAllPoints(PageRequest.of(page, limit));
    }

    @GetMapping("/rent/find")
    public RentPointWithTownDTO getByAddress(
            @RequestParam(value = "address", defaultValue = "")
                    String address
    ){
        return rentPointSpringDataService.findByAddress(address);
    }

    @GetMapping("/rent/findJPQL")
    public RentPointWithTownDTO getByAddressJPQL(
            @RequestParam(value = "id", defaultValue = "0")
                    Integer id
    ){
        return rentPointSpringDataService.findByIdFetchTown(id);
    }

    @GetMapping("/rent/findNative")
    public RentPointWithTownDTO getByAddressNative(
            @RequestParam(value = "id", defaultValue = "0")
                    Integer id
    ){
        return rentPointSpringDataService.findByIdNative(id);
    }

    @GetMapping("/rent/spec")
    public RentPointWithTownDTO getSpec(){
        return rentPointSpringDataService.findWithSpec();
    }

    @GetMapping("/rent/proj")
    public RentPointProj getProj( @RequestParam(value = "id", defaultValue = "0")
                                              Integer id){
        return rentPointSpringDataService.getProj(id);
    }
}
