package com.example.MovieCollection;

import org.springframework.beans.factory.annotation.Autowired;
import static org.apache.commons.lang3.StringUtils.isBlank;
import org.springframework.stereotype.Service;

import com.example.Repository.MovieRepo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.opencsv.CSVReader;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Column;

@Service
public class MovieService extends InputValidation {
		@Autowired
        private MovieRepo repo; 
		private TmdbSearch searchApi = new TmdbApi("01b13b38fd2da4ca845144d2cedd9762").getSearch();
        public MovieService() {
        }
        //initializes & puts all data from .csv file into database
        @PostConstruct
        public void initMovieCollection() {
        	String path = "the_oscar_award.csv";
    		String[] line;
    		try {
    			CSVReader csvReader = new CSVReader(new FileReader(path));
    			//reads line to skip category portion of file
    			line = csvReader.readNext();
    			if(line!=null) {
    				//int id = 1;
        			//boolean skip = false;
    				while((line = csvReader.readNext()) != null) {
    					//0:yearReleased, 1:yearNominated, 3:category, 4:awardee, 5:title, 6:awardStatus
    					if(line[5] == null)
    						line[5] = "N/A";
    					//id, title, category, yearReleased, yearNominated, awardee, status
    					Movie mov = new Movie(line[5],line[3].toUpperCase(),Integer.parseInt(line[0]),Integer.parseInt(line[1]),line[4],Boolean.parseBoolean(line[6]));
    					//movies.add(mov);
    					repo.save(mov);
    					//id++;
    				}
    			}
    		} catch(FileNotFoundException e) {
    			e.printStackTrace();
    		} catch(IOException e) {
    			e.printStackTrace();
    		}
        }
        public String getImageLink(String poster) {
        	return "https://image.tmdb.org/t/p/w500" + poster;
        }
        public String getWatchLink(int tmdbId) {
        	return "https://www.themoviedb.org/movie/" + tmdbId+"/watch";
        }
    //when searching for movie(s), updates untouched records w/ data from TMDB API
    //would have taken forever to load up at start otherwise    
    public List<Movie> updateSearchedResults(List<Movie> list) {
    	for(int i = 0; i < list.size(); i++) {
    		Movie mov = list.get(i);
    		if(mov.getPlot() == null) {
    			MovieResultsPage results;
    			if(!isBlank(mov.getTitle())) {
    				int year = mov.getYearReleased();
    				while((results = searchApi.searchMovie(mov.getTitle(), year, null, true, 0)).getTotalResults() == 0 && year <= 2020)
    					year++;
    				for(MovieDb m : results) {
    					int apiId = m.getId();
    					mov.setTmdbId(apiId);
    					mov.setImageLink(getImageLink(m.getPosterPath()));
    					mov.setLink(getWatchLink(apiId));
    					mov.setPlot(m.getOverview());
    					break;
    				}
    				System.out.println(mov);    				
    				repo.save(mov);
    			}
    		}
    	}
    	return list;
    }
    //DATABASE QUERY METHODS
    public Movie findByIdLoop(int id) throws IllegalArgumentException {
    	//iDValidation(id);
    	List<Movie> temp = new ArrayList<Movie>();
    	temp.add(repo.findById(id));
    	return updateSearchedResults(temp).get(0);
    }
    public Movie findById(String idInput) throws IllegalArgumentException {
    	int id = iDValidation(idInput);
    	List<Movie> temp = new ArrayList<Movie>();
    	temp.add(repo.findById(id));
    	return updateSearchedResults(temp).get(0);
    }
    public boolean deleteMovieByIdFromDatabase(int id) {
    	if(id < 1 || (repo.findById(id) == null))
    		return false;
    	else {
    		repo.deleteById(id);
    		return true;
    	}
    }
    /*
    public boolean addMovieToDatabase(Movie mov) {
    	if(mov.getId() < 1 || (repo.findById(mov.getId())) != null)
    		return false;
    	else {
    		repo.save(mov);
    		return true;
    	}
    }
  //haven't tested this yet
    public boolean updateMovieInDatabase(int id, Movie mov) {
    	Movie temp = repo.findById(id);
    	if(id < 1 || temp == null)
    		return false;
    	else {
    		temp.setAwardee(mov.getAwardee());
    		temp.setAwardStatus(mov.isAwardStatus());
    		temp.setCategory(mov.getCategory());
    		temp.setImageLink(mov.getImageLink());
    		temp.setLink(mov.getLink());
    		temp.setPlot(mov.getPlot());
    		temp.setTitle(mov.getTitle());
    		temp.setTmdbId(mov.getTmdbId());
    		temp.setYearNominated(mov.getYearNominated());
    		temp.setYearReleased(mov.getYearReleased());
    		repo.save(temp);
    		return true;
    	}
    }
    */
    //below are methods to search for movies based on year nominated, category, award status, etc
    public List<Movie> findByYear(String yearInput) {
    	int year = yearNominatedValidation(yearInput);
    	return updateSearchedResults(repo.findByYear(year));
    }
    public List<Movie> findWinnersByCategory(String category, String yearInput) {
    	category = categoryLengthValidation(category);
    	int year = yearNominatedValidation(yearInput);
    	return updateSearchedResults(repo.findWinnerByYear(category, year));
    }
    public List<Movie> findNominationsByCategoryAndYear(String category, String yearInput) {
    	category = categoryLengthValidation(category);
    	int year = yearNominatedValidation(yearInput);
    	return updateSearchedResults(repo.findNominationsByYear(category, year));
    }
    public List<Movie> findNominationsBetweenYears(String startInput, String endInput) {
    	int start = yearNominatedValidation(startInput);
    	int end = yearNominatedValidation(endInput);
    	dateRangeValidation(start, end);
    	return updateSearchedResults(repo.findByYearNominatedBetween(start, end));
    }
    //possible method to use for custom searches
    public List<Movie> findByTitle(String title) {
    	title = titleLengthValidation(title);
    	//need to use _ since some movies use - in the title and the path would be messy
    	return updateSearchedResults(repo.findByTitleContainingIgnoreCase(title));
    }
    public List<Movie> findByCategory(String category) {
    	category = categoryLengthValidation(category);
    	return updateSearchedResults(repo.findByCategoryContainingIgnoreCase(category));
    }
    public List<Movie> findByTitleAndCategoryAndYear(String category, String title, String yearInput) {
    	category = categoryLengthValidation(category);
    	title = titleLengthValidation(title);
    	int year = yearNominatedValidation(yearInput);
    	return updateSearchedResults(repo.findNominationsByTitleAndYear(category, title, year));
    }
    public List<Movie> findByCategoryBetweenYears(String category, String startInput, String endInput) {
    	int start = yearNominatedValidation(startInput);
    	int end = yearNominatedValidation(endInput);
    	dateRangeValidation(start, end);
    	category = categoryLengthValidation(category);
    	return updateSearchedResults(repo.findBetweenYearsAndByCategory(start, end, category));
    }
    public List<Movie> findWinnersByCategory(String category) {
    	category = categoryLengthValidation(category);
    	return updateSearchedResults(repo.findByAwardStatusTrueAndCategoryContaining(category));
    }
}
