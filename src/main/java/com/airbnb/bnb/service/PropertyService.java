package com.airbnb.bnb.service;

import com.airbnb.bnb.entity.Property;
import com.airbnb.bnb.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {


    private PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }


    public Property insertDetqails(@RequestBody Property property){

        return propertyRepository.save(property);
    }

    public Property addproperty(Property property) {
        Property saved = propertyRepository.save(property);
        return saved;
    }


    public List<Property> serchProperty(String cityName) {

        return propertyRepository.serchProperty(cityName);
    }


}
