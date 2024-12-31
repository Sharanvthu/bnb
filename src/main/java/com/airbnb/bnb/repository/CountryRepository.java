package com.airbnb.bnb.repository;

import com.airbnb.bnb.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}