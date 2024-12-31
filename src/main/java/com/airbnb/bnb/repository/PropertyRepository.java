package com.airbnb.bnb.repository;

import com.airbnb.bnb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
//
//    @Query("select p from Property p JOIN City c ON p.city=c.id  where c.name=:cityName")
//    List<Property> serchProperty(
//           @Param("cityName") String cityName
//    );


//@Query("select p from Property p INNER JOIN p.city c where c.name=:cityName")
//    List<Property> serchProperty(
//            @Param("cityName") String cityName
//    );





    @Query("select p from Property p  JOIN p.city c where c.name=:cityName")
    List<Property> serchProperty(
            @Param("cityName") String cityName
    );


//@Query("select p from Property p JOIN p.city c JOIN p.country co  where c.name=:name or co.name=:name")



}