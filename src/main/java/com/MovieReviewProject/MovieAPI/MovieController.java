package com.MovieReviewProject.MovieAPI;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getaAllMovies(){
        return new ResponseEntity<List<Movie>>(movieService.allMovies(), HttpStatus.OK);
    }
    //remember , response entity type parameter is the body of response

    @GetMapping ("/{id}") //fetching with object id
    public ResponseEntity<Optional<Movie>> getSingleMovieById(@PathVariable("id") ObjectId id){//remmeber ids in this app are ObjectId type

        return new ResponseEntity<>(movieService.singleMovieById(id),HttpStatus.OK);
    } //even optional type is returned as body , and set as Type parameter

    @GetMapping("/find/{imdbId}")// fetching using imdb id
    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable("imdbId") String ImdbId){
        return new ResponseEntity<>(movieService.singleMovie(ImdbId), HttpStatus.OK);
    }
}
