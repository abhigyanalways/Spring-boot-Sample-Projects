package com.MovieReviewProject.MovieAPI;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {

    //making a custom (not fully) method
    public Optional<Movie> findMovieByImdbId(String ImdbId);
}
