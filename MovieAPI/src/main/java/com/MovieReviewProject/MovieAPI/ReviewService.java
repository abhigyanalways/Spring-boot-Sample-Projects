package com.MovieReviewProject.MovieAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    MongoTemplate mongoTemplate; //using template+query
    public Review createReview(String reviewBody , String imdbId){
        Review review=new Review(reviewBody);
        reviewRepository.insert(review);

        Query query=new Query();
        query.addCriteria(Criteria.where("imdbId").is(imdbId));

        mongoTemplate.update(Movie.class)
                .matching(query)
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;

    }


}
