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
	private final static String[] SINGLETON_CATEGORIES = {
			"ACTOR IN A LEADING ROLE", "ACTOR IN A SUPPORTING ROLE", "ACTRESS IN A LEADING ROLE",
			"ACTRESS IN A SUPPORTING ROLE", "ART DIRECTION", "CINEMATOGRAPHY", "ART DIRECTION",
			"DANCE DIRECTION", "FILM EDITING", "ENGINEERING EFFECTS", "SPECIAL EFFECTS",
			"VISUAL EFFECTS", "BEST MOTION PICTURE", "BEST PICTURE", "OUTSTANDING PICTURE",
			"OUTSTANDING MOTION PICTURE", "OUTSTANDING PRODUCTION", "SOUND RECORDING",
			"SOUND EDITING", "SOUND MIXING", "SOUND EFFECTS EDITING", "ASSISTANT DIRECTOR",
			"COSTUME DESIGN", "MAKEUP", "ANIMATED FEATURE FILM", "INTERNATIONAL FEATURE FILM",
			"FOREIGN LANGUAGE FILM"
			};
	
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
	//singleton api method
	@RequestMapping("/singleton/winning/category/{category}/year/{year}")
	public Movie getSingleMovieWinnerByCategoryAndYear(@PathVariable String category, @PathVariable int year) {
			category = category.replaceAll("[-_]"," ").toUpperCase();
			for(int i = 0; i < SINGLETON_CATEGORIES.length; i++) {
				if(category.equals(SINGLETON_CATEGORIES[i])) {
					List<Movie> temp = topicService.findWinnersByCategory(category, year);
					if(temp!=null)
						return temp.get(0);
				}
			}
			return new Movie();
	}
	@RequestMapping("/category/{category}")
	public List<Movie> getCategory(@PathVariable String category) {
		return topicService.findByCategory(category);
	}
	//possible method to reduce amount of REST Endpoints
	@RequestMapping("/api/category/{category}/year/{year}")
	public List<Movie> getCategoryAndYear(@PathVariable String category, @PathVariable int year) {
		return topicService.findNominationsByCategoryAndYear(category, year);
	}
	//possible method to reduce amount of REST Endpoints
		@RequestMapping("/category/{category}/year/{year}")
		public ModelAndView getCategoryAndYearWebsite(@PathVariable String category, @PathVariable int year, Model model) {
			List<Movie> movies = topicService.findNominationsByCategoryAndYear(category, year);
			return modelMaker(movies, model);
		}
	@RequestMapping("/api/range/start/{start}/end/{end}")
	public List<Movie> getMoviesWithinRange(@PathVariable int start, @PathVariable int end) {
        return topicService.findNominationsBetweenYears(start, end);
	}
	@RequestMapping("/range/start/{start}/end/{end}")
	public ModelAndView getMoviesWithinRangeWeb(@PathVariable int start, @PathVariable int end, Model model) {
        List<Movie> movies = topicService.findNominationsBetweenYears(start, end);
        return modelMaker(movies, model);
	}
	@RequestMapping("/api/category/{category}/start/{start}/end/{end}")
	public List<Movie> getNominationTypeWithinRange(@PathVariable String category, @PathVariable int start, @PathVariable int end) {
        return topicService.findByCategoryBetweenYears(category, start, end);
	}
	@RequestMapping("/category/{category}/start/{start}/end/{end}")
	public ModelAndView getNominationTypeWithinRangeWeb(@PathVariable String category, @PathVariable int start, @PathVariable int end, Model model) {
        List<Movie> movies = topicService.findByCategoryBetweenYears(category, start, end);
        return modelMaker(movies, model);
	}
	@RequestMapping("/api/winning/category/{category}")
	public List<Movie> getWinnersByCategory(@PathVariable String category) {
		return topicService.findWinnersByCategory(category);
	}
	@RequestMapping("/winning/category/{category}")
	public ModelAndView getWinnersByCategoryWeb(@PathVariable String category, Model model) {
		List<Movie> movies = topicService.findWinnersByCategory(category);
        return modelMaker(movies, model);
	}
	@RequestMapping("/api/winning/category/{category}/year/{year}")
	public List<Movie> getWinnersByCategoryAndYear(@PathVariable String category, @PathVariable int year) {
		return topicService.findWinnersByCategory(category, year);
	}
	@RequestMapping("/winning/category/{category}/year/{year}")
	public ModelAndView getWinnersByCategoryAndYearWeb(@PathVariable String category, @PathVariable int year, Model model) {
		List<Movie> movies = topicService.findWinnersByCategory(category, year);
		return modelMaker(movies, model);
	}
	@GetMapping("/api/search/category-year")
	public List<Movie> getSearchCategoryAndYear(@RequestParam(name="category") String category, @RequestParam(name="year") int year) {
		return topicService.findNominationsByCategoryAndYear(category, year);
	}
	@GetMapping("/search/category-year")
	public ModelAndView getSearchCategoryAndYearWeb(@RequestParam(name="category") String category, @RequestParam(name="year") int year, Model model) {
		List<Movie> movies = topicService.findNominationsByCategoryAndYear(category, year);
		return modelMaker(movies, model);
	}
	// search function api
    @GetMapping("/api/search-movie")
    public List<Movie> getSearchResults(@RequestParam(name = "Title") String title) {
    	return topicService.findByTitle(title);
    }
    @GetMapping("/search-movie")
    public ModelAndView getSearchResultsWeb(@RequestParam(name = "Title") String title, Model model) {
    	List<Movie> movies = topicService.findByTitle(title);
    	return modelMaker(movies, model);
    }
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
    //handles illegal arguments to let the user know
    @ExceptionHandler({IllegalArgumentException.class})
    public ModelAndView argumentError(Exception e, Model model) {
    	model.addAttribute("error", e.getMessage());
    	ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("illegalArgument");
		return modelAndView;
    }
    
    public ModelAndView modelMaker(List<Movie> mov, Model model) {
    	model.addAttribute("movie", mov);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
    }
}
