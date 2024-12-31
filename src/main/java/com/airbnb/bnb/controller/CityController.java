package com.airbnb.bnb.controller;

import com.airbnb.bnb.entity.City;
import com.airbnb.bnb.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/city")
public class CityController {

private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @PostMapping("add")
    public City insertCity(@RequestBody City city,@PathVariable Long id){

        return cityService.insertDetails(city,id);

    }
}
