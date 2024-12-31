package com.airbnb.bnb.service;

import com.airbnb.bnb.entity.Country;
import com.airbnb.bnb.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService {


private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country insertCountry(Country country, Long id){

        return countryRepository.save(country);

    }


}
