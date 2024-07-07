package com.MovieReviewProject.MovieAPI;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public List<Movie> fetchAllMovies() {
       return movieRepository.findAll();

    }

    //finding by imdb id
    public Optional<Movie> singleMovie(String imdbId){
        return movieRepository.findMovieByImdbId(imdbId);
    }

    //to find by id (object id)
    public Optional<Movie> movieByTitle(String title){
        return movieRepository.findMovieByTitle(title);
    }
}
