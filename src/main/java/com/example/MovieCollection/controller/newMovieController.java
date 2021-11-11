package com.example.MovieCollection.controller;

import com.example.MovieCollection.Movie;
import com.example.MovieCollection.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/awardmovies")
public class newMovieController {

    @Autowired
    private MovieService topicService;

    //return all movies
    //@RequestMapping("/animatedfeature/2020")
    @RequestMapping("/")
    public List<Movie> getAllMovies(){
        return topicService.getAllMovies();
    }

    //return individual movie
    //@RequestMapping("/animatedfeature/2020/{id}")
    @RequestMapping("/{id}")
    public Movie getMovie(@PathVariable String id){
        return topicService.getMovie(id);
    }

    //add movie
    //@RequestMapping(method = RequestMethod.POST, value ="/animatedfeature/2020")
    @RequestMapping(method = RequestMethod.POST, value ="/")
    public void addMovie(@RequestBody Movie movie){
        topicService.addMovie(movie);
    }

    //update movie
    //@RequestMapping(method = RequestMethod.PUT, value ="/animatedfeature/2020/{id}")
    @RequestMapping(method = RequestMethod.POST, value ="/{id}")
    public void updateMovie(@RequestBody Movie movie, @PathVariable String id){
        topicService.updateMovie(id, movie);
    }

    //delete movie
    //@RequestMapping(method = RequestMethod.DELETE, value ="/animatedfeature/2020/{id}")
    @RequestMapping(method = RequestMethod.DELETE, value ="/{id}")
    public void deleteMovie(@PathVariable String id){
        topicService.deleteMovie(id);
    }
}
