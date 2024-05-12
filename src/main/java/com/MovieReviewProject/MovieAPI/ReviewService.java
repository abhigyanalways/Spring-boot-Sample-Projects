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


    //the custom query way to interact with mongodb
    //we shall see mongo template+query method

    @Autowired
    private MongoTemplate mongoTemplate;

    public  Review createReview(String reviewBody , String imdbId){


        //creating a review
        Review review=new Review();
        review.setBody(reviewBody);
        //saving it to review collection
        reviewRepository.insert(review);

        //preparing query for mongo template
        Query query = new Query();
        query.addCriteria(Criteria.where("imdbId").is(imdbId));

        //using mongo template
        mongoTemplate.update(Movie.class)//selecting the collection
                .matching(query)//selecting that document
                .apply(new Update().push("reviewIds" ).value(review))//updating fields
                .first();

        return review;







    }
}
