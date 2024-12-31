package com.airbnb.bnb.repository;

import com.airbnb.bnb.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}