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

    public List<Movie> allMovies(){
        return movieRepository.findAll();
    }

    public Optional<Movie> singleMovieById(ObjectId id) {//optional ensures no null pointers
        return movieRepository.findById(id);
    }

    public Optional<Movie> singleMovie(String imdbId) {//optional ensures no null pointers
        return movieRepository.findMovieByImdbId(imdbId);
    }
}
