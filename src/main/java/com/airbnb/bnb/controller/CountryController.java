package com.airbnb.bnb.controller;


import com.airbnb.bnb.entity.Country;
import com.airbnb.bnb.repository.CountryRepository;
import com.airbnb.bnb.service.CountryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/country")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/add")
    public Country insertCountry(@RequestBody Country country, @PathVariable Long id){

        return countryService.insertCountry(country,id);
    }

}
