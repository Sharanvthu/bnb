package com.airbnb.bnb.service;

import com.airbnb.bnb.entity.City;
import com.airbnb.bnb.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }



    public City insertDetails(City city,Long id){
       return cityRepository.save(city);
    }


}
