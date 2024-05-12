package com.MovieReviewProject.MovieAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController { //we need only the post method here (choice)

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String , String > payload) { //the DTO is a Map!!

        return new ResponseEntity<>(reviewService.createReview //returns review
                (payload.get("reviewBody"), payload.get("imdbId")),//using the map<> dto
                HttpStatus.CREATED);//a bit different code for creation (200 -> 201)
    }
}
