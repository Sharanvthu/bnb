package com.airbnb.bnb.repository;

import com.airbnb.bnb.entity.AppUser;
import com.airbnb.bnb.entity.Property;
import com.airbnb.bnb.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {


    @Query("select r from Review r where r.property=:property and r.appUser=:user")
    Review findByUserAndProperty(

           @Param("user") AppUser user,
           @Param("property") Property property

    );

    @Query("select r from Review r where r.appUser=:user")
    List<Review> findReviewsByUser(
           @Param("user") AppUser user
    );

}