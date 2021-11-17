package com.example.MovieCollection.controller;

import com.example.MovieCollection.Movie;
import com.example.MovieCollection.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
//@Controller
@RequestMapping("/awardmovies")
public class newMovieController {

	//categories for rewards
	private final static String ACTOR = "ACTOR";
	private final static String ACTRESS = "ACTRESS";
	private final static String ACT_DIRECTION = "ART DIRECTION";
	private final static String DANCE_DIRECTION = "DANCE DIRECTION";
	private final static String CINEMA = "CINEMATOGRAPHY";
	private final static String WRITING = "WRITING";
	private final static String DIRECTING = "DIRECTING";
	private final static String ENGINEERING_EFFECTS = "ENGINEERING EFFECTS";
	private final static String SPECIAL_EFFECTS = "SPECIAL EFFECTS";
	private final static String FILM_EDITING = "FILM EDITING";
	private final static String VISUALS = "VISUAL EFFECTS";
	private final static String BEST_MOTION_PICTURE = "BEST MOTION PICTURE";
	private final static String BEST_PICTURE = "BEST PICTURE";
	private final static String OUTSTANDING_PICTURE = "OUTSTANDING PICTURE";
	private final static String OUTSTANDING_MOTION_PICTURE = "OUTSTANDING MOTION PICTURE";
	private final static String OUTSTANDING_PRODUCTION = "OUTSTANDING PICTURE";
	private final static String SOUND_RECORDING = "SOUND RECORDING";
	private final static String SOUND_EDITING = "SOUND EDITING";
	private final static String SOUND = "SOUND";
	private final static String MUSIC = "MUSIC";
	private final static String SHORT_SUBJECT = "SHORT SUBJECT";
	private final static String SHORT_FILM = "SHORT FILM";
	private final static String ASSISTANT_DIRECTOR = "ASSISTANT DIRECTOR";
	private final static String HONORARY = "HONORARY AWARD";
	private final static String SPECIAL = "SPECIAL AWARD";
	private final static String COSTUME = "COSTUME DESIGN";
	private final static String MAKEUP = "MAKEUP";
	private final static String ANIMATED = "ANIMATED FEATURE FILM";
	private final static String INTERNAT_FILM = "INTERNATIONAL FEATURE FILM";
	private final static String FOREIGN_FILM = "FOREIGN LANGUAGE FILM";
	private final static String DOCUMENTARY = "DOCUMENTARY";
	
	private final static int MIN_NOMINATION_YEAR = 1928;
	private final static int MAX_NOMINATION_YEAR = 2020;

    @Autowired
    private MovieService topicService = new MovieService();

	@RequestMapping("/apiDoc")
	public ModelAndView getApiDoc(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("apiDoc");
		return modelAndView;
	}

	// return all movies within the input year
	@RequestMapping("/year/{year}")
    public List<Movie> getForYear(@PathVariable int year){
        return topicService.getMoviesNominatedForYear(year);
    }

    //return all movies nominated for the input year
    @RequestMapping("/animated-feature/{year}")
    public List<Movie> getAnimatedNominations(@PathVariable int year){
        return topicService.getCategoryNominationsForYear(ANIMATED, year);
    }
    
    @RequestMapping("/costume-design/{year}")
    public List<Movie> getCostumeNominations(@PathVariable int year) {
    	return topicService.getCategoryNominationsForYear(COSTUME, year);
    }

    //returns movie that won input award for input year
    @RequestMapping("/film-editing/{year}")
    public Movie getFilmEditingWinner(@PathVariable int year) {
    	return topicService.getWinner(FILM_EDITING, year);
    }
    
	//return individual movie
	@RequestMapping("/id/{id}")
    public Movie getAnimatedNominationsWithId( @PathVariable int id) {
    	return topicService.getMovie(id);
    }
    
	/*	// This RequestMapping overlapped with @RequestMapping("/animated-feature/{year}"), disabled it for now.
    //return individual movie
    @RequestMapping("/animatedfeature/{id}")
    public Movie getMovie(@PathVariable int id){
        return topicService.getMovie(id);
    }
	*/

    //add movie
    @RequestMapping(method = RequestMethod.POST, value ="/animatedfeature/2020")
    public void addMovie(@RequestBody Movie movie){
        topicService.addMovie(movie);
    }

    //update movie
    @RequestMapping(method = RequestMethod.PUT, value ="/animatedfeature/2020/{id}")
    public void updateMovie(@RequestBody Movie movie, @PathVariable int id){
        topicService.updateMovie(id, movie);
    }

    //delete movie
    @RequestMapping(method = RequestMethod.DELETE, value ="/animatedfeature/2020/{id}")
    public void deleteMovie(@PathVariable int id){
        topicService.deleteMovie(id);
    }
}
