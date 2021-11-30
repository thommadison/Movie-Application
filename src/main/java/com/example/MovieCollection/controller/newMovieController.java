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
	private final static String ART_DIRECTION = "ART DIRECTION";
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
	private final static int ORDER_FROM_OLDEST = 1;
	private final static int ORDER_FROM_LATEST = 10394;
	private final static int MAX_NO_DISPLAY = 10;

    @Autowired
    private MovieService topicService;

	@RequestMapping("/index")
	public ModelAndView getIndex(@RequestParam(name="movie", required = false, defaultValue = "Movie not found") String name, Model model){
		List<Movie> list = new ArrayList<Movie>();
		// Check if movie title is blank
		// From oldest
		//for (int i = ORDER_FROM_OLDEST; i < ORDER_FROM_OLDEST+MAX_NO_DISPLAY;i++)
		//	list.add(topicService.findById(i));
		// From latest
		for (int i=ORDER_FROM_LATEST-3; i >ORDER_FROM_LATEST-3-MAX_NO_DISPLAY;i--)
			list.add(topicService.findById(i));
		model.addAttribute("movie", list);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
		// testing 
		/*Arrays.asList(
			topicService.findById(1),
			topicService.findById(2),
			topicService.findById(3),
			topicService.findById(4),
			topicService.findById(5),
			topicService.findById(6),
			topicService.findById(7),
			topicService.findById(8),
			topicService.findById(9),
			topicService.findById(10)
			
		);*/
	}
	// api document page holder
	@RequestMapping("/apiDoc")
	public ModelAndView getApiDoc(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("apiDoc");
		return modelAndView;
	}
	// returns the movies of animated feature award within the input year
    @RequestMapping("/animated-feature/{year}")
    public List<Movie> getAnimatedNominations(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(ANIMATED, year);
    }
	// returns the movies of actor award within the input year
    @RequestMapping("/actor/{year}")
    public List<Movie> getActorNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(ACTOR, year);
    }
	// returns the movies of actress award within the input year
    @RequestMapping("/actress/{year}")
    public List<Movie> getActressNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(ACTRESS, year);
    }
	// returns the movies of art direction award within the input year
    @RequestMapping("/art-direction/{year}")
    public List<Movie> getArtDirectionNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(ART_DIRECTION, year);
    }
	// returns the movies of dance direction award within the input year
    @RequestMapping("/dance-direction/{year}")
    public List<Movie> getDanceDirectionNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(DANCE_DIRECTION, year);
    }
	// returns the movies of cinematography award within the input year
    @RequestMapping("/cinematography/{year}")
    public List<Movie> getCinemaNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(CINEMA, year);
    }
	// returns the movies of writing award within the input year
    @RequestMapping("/writing/{year}")
    public List<Movie> getWritingNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(WRITING, year);
    }
	// returns the movies of directing award within thg input year
    @RequestMapping("/directing/{year}")
    public List<Movie> getDirectingNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(DIRECTING, year);
    }
	// returns the movies if engineering effects award within the input year
    @RequestMapping("/engineering-effects/{year}")
    public List<Movie> getEngineeringEffectsNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(ENGINEERING_EFFECTS, year);
    }
	// returns the movies of costume design award within the input year
    @RequestMapping("/costume-design/{year}")
    public List<Movie> getCostumeNominations(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(COSTUME, year);
    }
    // returns movies of film editing award within input year
    @RequestMapping("/film-editing/{year}")
    public List<Movie> getFilmEditingWinner(@PathVariable int year) {
    	return topicService.findWinnersByCategory(FILM_EDITING, year);
    }
	// returns movies of music award within input year
    @RequestMapping("/music/{year}")
	public List<Movie> getMusicNominations(@PathVariable int year) {
		return topicService.findWinnersByCategory(MUSIC, year);
	}
	// returns all movies within input year
    @RequestMapping("year/{year}")
    public List<Movie> getMoviesByYear(@PathVariable int year) {
    	return topicService.findByYear(year);
    }
	// return individual movie with id
	@RequestMapping("/id/{id}")
    public Movie getMovieWithId( @PathVariable int id) {
    	return topicService.findById(id);
    }
	// search function api
    @GetMapping("/search-movie")
    public List<Movie> getSearchResults(@RequestParam(name = "Title") String title) {
    	return topicService.findByTitle(title);
    }
	/*	// This RequestMapping overlapped with @RequestMapping("/animated-feature/{year}"), disabled it for now.
    //return individual movie
    @RequestMapping("/animatedfeature/{id}")
    public Movie getMovie(@PathVariable int id){
        return topicService.getMovie(id);
    }*/
    // add movie
    @RequestMapping(method = RequestMethod.POST, value ="/add-movie")
    public void addMovie(@RequestBody Movie movie){
        topicService.addMovieToDatabase(movie);
    }
    // update movie
    @RequestMapping(method = RequestMethod.PUT, value ="/update-movie/id/{id}")
    public void updateMovie(@RequestBody Movie movie, @PathVariable int id){
        topicService.updateMovieInDatabase(id, movie);
    }
    // delete movie
    @RequestMapping(method = RequestMethod.DELETE, value ="/delete-movie/{id}")
    public void deleteMovie(@PathVariable int id){
        topicService.deleteMovieByIdFromDatabase(id);
    }
}
