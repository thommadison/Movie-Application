package com.example.MovieCollection.controller;

import com.example.MovieCollection.Movie;
import com.example.MovieCollection.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

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
    private MovieService topicService;

	@RequestMapping("/index")
	public ModelAndView getIndex(@RequestParam(name="movie", required = false, defaultValue = "Movie not found") String name, Model model){
		model.addAttribute("movie", Arrays.asList(
			// get movie by id
			topicService.getMovie(1),
			topicService.getMovie(2),
			topicService.getMovie(3),
			topicService.getMovie(4),
			topicService.getMovie(5),
			topicService.getMovie(6),
			topicService.getMovie(7),
			topicService.getMovie(8),
			topicService.getMovie(9),
			topicService.getMovie(10)
		));
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

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
    @RequestMapping("/animated-feature/{year}")
    public List<Movie> findAnimatedNominations(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(ANIMATED, year);
    }
    @RequestMapping("/costume-design/{year}")
    public List<Movie> findCostumeNominations(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(COSTUME, year);
    }
  //returns movie that won input award for input year
    @RequestMapping("/film-editing/{year}")
    public List<Movie> getFilmEditingWinner(@PathVariable int year) {
    	return topicService.findWinnersByCategory(FILM_EDITING, year);
    }
    @RequestMapping("/music/{year}")
	public List<Movie> getMusicNominations(@PathVariable int year) {
		return topicService.findWinnersByCategory(MUSIC, year);
	}
    @RequestMapping("test-year/{year}")
    public List<Movie> testYear(@PathVariable int year) {
    	return topicService.testYear(year);
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
