package com.example.MovieCollection.controller;

import static org.apache.commons.lang3.StringUtils.isBlank;
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
	private final static int ORDER_FROM_LATEST = 4671;
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
		for (int i=ORDER_FROM_LATEST, j = ORDER_FROM_LATEST; j > ORDER_FROM_LATEST-MAX_NO_DISPLAY; i--, j--) {
			Movie temp = topicService.findById(i);
			if(!isBlank(temp.getTitle()))
				list.add(topicService.findById(i));
			else
				j++;
		}
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
	//methods below return either the nominated movies or winners of a certain award category
	// returns the movies of animated feature award within the input year
    @RequestMapping("/animated-feature/{year}")
    public List<Movie> getAnimatedNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(ANIMATED, year);
    }
    @RequestMapping("/winning/animated-feature/{year}")
    public List<Movie> getAnimatedWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(ANIMATED, year);
    }
	// returns the movies of actor award within the input year
    @RequestMapping("/actor/{year}")
    public List<Movie> getActorNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(ACTOR, year);
    }
    @RequestMapping("/winning/actor/{year}")
    public List<Movie> getActorWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(ACTOR, year);
    }
	// returns the movies of actress award within the input year
    @RequestMapping("/actress/{year}")
    public List<Movie> getActressNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(ACTRESS, year);
    }
    @RequestMapping("/winning/actress/{year}")
    public List<Movie> getActressWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(ACTRESS, year);
    }
	// returns the movies of art direction award within the input year
    @RequestMapping("/art-direction/{year}")
    public List<Movie> getArtDirectionNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(ART_DIRECTION, year);
    }
    @RequestMapping("/winning/art-direction/{year}")
    public List<Movie> getArtDirectionWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(ART_DIRECTION, year);
    }
	// returns the movies of dance direction award within the input year
    @RequestMapping("/dance-direction/{year}")
    public List<Movie> getDanceDirectionNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(DANCE_DIRECTION, year);
    }
    @RequestMapping("/winning/dance-direction/{year}")
    public List<Movie> getDanceDirectionWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(DANCE_DIRECTION, year);
    }
	// returns the movies of cinematography award within the input year
    @RequestMapping("/cinematography/{year}")
    public List<Movie> getCinemaNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(CINEMA, year);
    }
    @RequestMapping("/winning/cinematography/{year}")
    public List<Movie> getCinemaWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(CINEMA, year);
    }
	// returns the movies of writing award within the input year
    @RequestMapping("/writing/{year}")
    public List<Movie> getWritingNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(WRITING, year);
    }
    @RequestMapping("/winning/writing/{year}")
    public List<Movie> getWritingWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(WRITING, year);
    }
	// returns the movies of directing award within thg input year
    @RequestMapping("/directing/{year}")
    public List<Movie> getDirectingNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(DIRECTING, year);
    }
    @RequestMapping("/winning/directing/{year}")
    public List<Movie> getDirectingWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(DIRECTING, year);
    }
	// returns the movies if engineering effects award within the input year
    @RequestMapping("/engineering-effects/{year}")
    public List<Movie> getEngineeringEffectsNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(ENGINEERING_EFFECTS, year);
    }
    @RequestMapping("/winning/engineering-effects/{year}")
    public List<Movie> getEngineeringEffectsWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(ENGINEERING_EFFECTS, year);
    }
    @RequestMapping("/special-effects/{year}")
    public List<Movie> getSpecialEffectsNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(SPECIAL_EFFECTS, year);
    }
    @RequestMapping("/winning/special-effects/{year}")
    public List<Movie> getSpecialEffectsWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(SPECIAL_EFFECTS, year);
    }
    @RequestMapping("/visual-effects/{year}")
    public List<Movie> getVisualEffectsNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(VISUALS, year);
    }
    @RequestMapping("/winning/visual-effects/{year}")
    public List<Movie> getVisualEffectsWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(VISUALS, year);
    }
    @RequestMapping("/film-editing/{year}")
    public List<Movie> getFilmEditingNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(FILM_EDITING, year);
    }
    @RequestMapping("/winning/film-editing/{year}")
    public List<Movie> getFilmEditingWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(FILM_EDITING, year);
    }
    @RequestMapping("/best-motion-picture/{year}")
    public List<Movie> getBestMotionPictureNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(BEST_MOTION_PICTURE, year);
    }
    @RequestMapping("/winning/best-motion-picture/{year}")
    public List<Movie> getBestMotionPictureWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(BEST_MOTION_PICTURE, year);
    }
    @RequestMapping("/best-picture/{year}")
    public List<Movie> getBestPictureNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(BEST_PICTURE, year);
    }
    @RequestMapping("/winning/best-picture/{year}")
    public List<Movie> getBestPictureWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(BEST_PICTURE, year);
    }
    @RequestMapping("/outstanding-picture/{year}")
    public List<Movie> getOutstandingPictureNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(OUTSTANDING_PICTURE, year);
    }
    @RequestMapping("/winning/outstanding-picture/{year}")
    public List<Movie> getOutstandingPictureWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(OUTSTANDING_PICTURE, year);
    }
    @RequestMapping("/outstanding-motion-picture/{year}")
    public List<Movie> getOutstandingMotionPictureNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(OUTSTANDING_MOTION_PICTURE, year);
    }
    @RequestMapping("/winning/outstanding-motion-picture/{year}")
    public List<Movie> getOutstandingMotionPictureWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(OUTSTANDING_MOTION_PICTURE, year);
    }
    @RequestMapping("/outstanding-production/{year}")
    public List<Movie> getOutstandingProductionNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(OUTSTANDING_PRODUCTION, year);
    }
    @RequestMapping("/winning/outstanding-production/{year}")
    public List<Movie> getOutstandingProductionWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(OUTSTANDING_PRODUCTION, year);
    }
    @RequestMapping("/sound-recording/{year}")
    public List<Movie> getSoundRecordingNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(SOUND_RECORDING, year);
    }
    @RequestMapping("/winning/sound-recording/{year}")
    public List<Movie> getSoundRecordingWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(SOUND_RECORDING, year);
    }
    @RequestMapping("/sound-editing/{year}")
    public List<Movie> getSoundEditingNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(SOUND_EDITING, year);
    }
    @RequestMapping("/winning/sound-editing/{year}")
    public List<Movie> getSoundEditingWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(SOUND_EDITING, year);
    }
    @RequestMapping("/sound/{year}")
    public List<Movie> getSoundNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(SOUND, year);
    }
    @RequestMapping("/winning/sound/{year}")
    public List<Movie> getSoundWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(SOUND, year);
    }
    @RequestMapping("/music/{year}")
    public List<Movie> getMusicNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(MUSIC, year);
    }
    @RequestMapping("/winning/music/{year}")
	public List<Movie> getMusicWinners(@PathVariable int year) {
		return topicService.findWinnersByCategory(MUSIC, year);
	}
    @RequestMapping("/short-subject/{year}")
    public List<Movie> getShortSubjectNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(SHORT_SUBJECT, year);
    }
    @RequestMapping("/winning/short-subject/{year}")
    public List<Movie> getShortSubjectWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(SHORT_SUBJECT, year);
    }
    @RequestMapping("/short-film/{year}")
    public List<Movie> getShortFilmNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(SHORT_FILM, year);
    }
    @RequestMapping("/winning/short-film/{year}")
    public List<Movie> getShortFilmWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(SHORT_FILM, year);
    }
    @RequestMapping("/assistant-director/{year}")
    public List<Movie> getAssistantDirectorNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(ASSISTANT_DIRECTOR, year);
    }
    @RequestMapping("/winning/assistant-director/{year}")
    public List<Movie> getAssistantDirectorWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(ASSISTANT_DIRECTOR, year);
    }
    @RequestMapping("/makeup/{year}")
    public List<Movie> getMakeupNomianted(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(MAKEUP, year);
    }
    @RequestMapping("/winning/makeup/{year}")
    public List<Movie> getMakeupWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(MAKEUP, year);
    }
    @RequestMapping("/international-film/{year}")
    public List<Movie> getInternationalNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(INTERNAT_FILM, year);
    }
    @RequestMapping("/winning/international-film/{year}")
    public List<Movie> getInternationalWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(INTERNAT_FILM, year);
    }
    @RequestMapping("/foreign-film/{year}")
    public List<Movie> getForeignNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(FOREIGN_FILM, year);
    }
    @RequestMapping("/winning/foreign-film/{year}")
    public List<Movie> getForeignWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(FOREIGN_FILM, year);
    }
    @RequestMapping("/documentary/{year}")
    public List<Movie> getDocumentaryNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(DOCUMENTARY, year);
    }
    @RequestMapping("/winning/documentary/{year}")
    public List<Movie> getDocumentaryWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(DOCUMENTARY, year);
    }
	// returns the movies of costume design award within the input year
    @RequestMapping("/costume-design/{year}")
    public List<Movie> getCostumeNominated(@PathVariable int year) {
    	return topicService.findNominationsByCategoryAndYear(COSTUME, year);
    }
    @RequestMapping("/winning/costume-design/{year}")
    public List<Movie> getCostumeWinners(@PathVariable int year) {
    	return topicService.findWinnersByCategory(COSTUME, year);
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
	@RequestMapping("/category/{category}")
	public List<Movie> getCategory(@PathVariable String category) {
		category = category.replaceAll("[-_]"," ");
		return topicService.findByCategory(category);
	}
	//possible method to reduce amount of REST Endpoints
	@RequestMapping("/category/{category}/year/{year}")
	public List<Movie> getCategoryAndYear(@PathVariable String category, @PathVariable String year) {
		try {
			category = category.replaceAll("[-_]"," ");
			return topicService.findNominationsByCategoryAndYear(category.toUpperCase(), Integer.parseInt(year));
		}
		catch(Exception e) {
			return getCategory(category);
		}
	}
	@RequestMapping("/range/start/{start}/end/{end}")
	public List<Movie> getMoviesWithinRange(@PathVariable int start, @PathVariable int end) {
        return topicService.findNominationsBetweenYears(start, end);
	}
	@RequestMapping("/category/{category}/start/{start}/end/{end}")
	public List<Movie> getNominationTypeWithinRange(@PathVariable String category, @PathVariable int start, @PathVariable int end) {
		category = category.replaceAll("[-_]"," ");
        return topicService.findByCategoryBetweenYears(category.toUpperCase(), start, end);
	}
	@GetMapping("/search/category-year")
	public List<Movie> getSearchCategoryAndYear(@RequestParam(name="category") String category, @RequestParam(name="year") String year) {
		try {
			category = category.replaceAll("[-_]"," ");
			return topicService.findNominationsByCategoryAndYear(category.toUpperCase(), Integer.parseInt(year));
		}
		catch (Exception e){
			//List<Movie> temp = new ArrayList<Movie>();
			return topicService.findByCategory(category.toUpperCase());
		}
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
