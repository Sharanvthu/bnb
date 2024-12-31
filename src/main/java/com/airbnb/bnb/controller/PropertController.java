package com.airbnb.bnb.controller;

import com.airbnb.bnb.entity.City;
import com.airbnb.bnb.entity.Country;
import com.airbnb.bnb.entity.Property;
import com.airbnb.bnb.repository.AppUserRepository;
import com.airbnb.bnb.repository.CityRepository;
import com.airbnb.bnb.repository.CountryRepository;
import com.airbnb.bnb.repository.PropertyRepository;
import com.airbnb.bnb.service.AppUserServiceImpl;
import com.airbnb.bnb.service.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")

public class  PropertController{


    private PropertyService propertyService;
    private CountryRepository countryRepository;
    private CityRepository cityRepository;
    private final AppUserRepository appUserRepository;
    private AppUserServiceImpl appUserService;

    public PropertController(PropertyService propertyService, CountryRepository countryRepository, CityRepository cityRepository,
                             AppUserRepository appUserRepository) {
        this.propertyService = propertyService;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.appUserRepository = appUserRepository;

    }


    @PostMapping
    public Property insertDetails(@RequestBody Property property , @RequestParam("countryId") long countryId ,
                                  @RequestParam("cityId") long cityId){

        Country country = countryRepository.findById(countryId).get();
        City city = cityRepository.findById(cityId).get();
        property.setCountry(country);
        property.setCity(city);
         Property property1 =   propertyService.addproperty(property);
          return  property1;
    }




    @GetMapping("/propertyresult")
    public List<Property> serchProperty(
           @RequestParam("cityName")  String cityName
    ){
        return propertyService.serchProperty(cityName);

    }







}
