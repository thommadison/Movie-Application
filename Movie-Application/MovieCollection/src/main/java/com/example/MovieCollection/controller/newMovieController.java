package com.example.MovieCollection.controller;

import com.example.MovieCollection.Movie;
import com.example.MovieCollection.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class newMovieController {

    @Autowired
    private MovieService topicService;

    //return all movies
    @RequestMapping("/awardmovies/animatedfeature/2020")
    public List<Movie> getAllMovies(){
        return topicService.getAllMovies();
    }

    //return individual movie
    @RequestMapping("/awardmovies/animatedfeature/2020/{id}")
    public Movie getMovie(@PathVariable String id){
        return topicService.getMovie(id);
    }

    //add movie
    @RequestMapping(method = RequestMethod.POST, value ="/awardmovies/animatedfeature/2020")
    public void addMovie(@RequestBody Movie movie){
        topicService.addMovie(movie);
    }

    //update movie
    @RequestMapping(method = RequestMethod.PUT, value ="/awardmovies/animatedfeature/2020/{id}")
    public void updateMovie(@RequestBody Movie movie, @PathVariable String id){
        topicService.updateMovie(id, movie);
    }

    //delete movie
    @RequestMapping(method = RequestMethod.DELETE, value ="/awardmovies/animatedfeature/2020/{id}")
    public void deleteMovie(@PathVariable String id){
        topicService.deleteMovie(id);
    }
}
