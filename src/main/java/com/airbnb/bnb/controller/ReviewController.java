package com.airbnb.bnb.controller;


import com.airbnb.bnb.entity.AppUser;
import com.airbnb.bnb.entity.Property;
import com.airbnb.bnb.entity.Review;
import com.airbnb.bnb.repository.PropertyRepository;
import com.airbnb.bnb.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

private ReviewRepository reviewRepository;
private PropertyRepository propertyRepository;

    public ReviewController(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }


    @RequestMapping("/createReview")
    public ResponseEntity<?> createReview(@RequestBody Review review,
                                               @AuthenticationPrincipal AppUser user,
                                               @RequestParam long propertyId
                                               ){

//        System.out.println(user.getName());
//        System.out.println(user.getEmail());

        Property property = propertyRepository.findById(propertyId).get();

        Review reviewDetails = reviewRepository.findByUserAndProperty(user, property);

        if(reviewDetails !=null){

           return new ResponseEntity<>("Review Exists",HttpStatus.CREATED);
        }

        review.setAppUser(user);
        review.setProperty(property);
        Review saveReview = reviewRepository.save(review);
        return new ResponseEntity<>(saveReview, HttpStatus.CREATED);
    }


    @GetMapping("/userReview")
    public List<Review> listReviewsOfUser(
            @AuthenticationPrincipal AppUser user

    ){

        List<Review> reviews = reviewRepository.findReviewsByUser(user);

    return reviews;
    }


}
