package com.MovieReviewProject.MovieAPI;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MovieRepository extends MongoRepository<Movie, ObjectId> {

   Optional<Movie> findMovieByImdbId(String imdbId);

   Optional<Movie> findMovieByTitle(String title);

}
