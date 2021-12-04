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
public class MovieService {
		@Autowired
        private MovieRepo repo; 
		private TmdbSearch searchApi = new TmdbApi("01b13b38fd2da4ca845144d2cedd9762").getSearch();
		private static String MIN_CATEGORY_ERROR = "Title cannot be less than 3 characters.";
		private static String MIN_TITLE_ERROR = "Title cannot be less than 3 characters.";
		private static String RANGE_ERROR = "Starting year cannot be less than ending year";
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
    					Movie mov = new Movie(line[5],line[3],Integer.parseInt(line[0]),Integer.parseInt(line[1]),line[4],Boolean.parseBoolean(line[6]));
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
    public Movie findById(int id) throws IllegalArgumentException {
    	if(id < 1)
    		throw new IllegalArgumentException("Id cannot be less than 1");
    	List<Movie> temp = new ArrayList<Movie>();
    	temp.add(repo.findById(id));
    	return updateSearchedResults(temp).get(0);
    }
    public boolean deleteMovieByIdFromDatabase(int id) {
    	Movie temp;
    	if(id < 1 || (temp = repo.findById(id)) == null)
    		return false;
    	else {
    		repo.deleteById(id);
    		return true;
    	}
    }
    public boolean addMovieToDatabase(Movie mov) {
    	Movie temp;
    	if(mov.getId() < 1 || (temp = repo.findById(mov.getId())) != null)
    		return false;
    	else {
    		repo.save(mov);
    		return true;
    	}
    }
    public List<Movie> findByYear(int year) {
    	return updateSearchedResults(repo.findByYear(year));
    }
    public List<Movie> findWinnersByCategory(String category, int year) throws IllegalArgumentException {
    	category = category.replaceAll("[-_]"," ").toUpperCase();
    	if(category.length() < 5)
    		throw new IllegalArgumentException(MIN_CATEGORY_ERROR);
    	return updateSearchedResults(repo.findWinnerByYear(category, year));
    }
    public List<Movie> findNominationsByCategoryAndYear(String category, int year) throws IllegalArgumentException {
    	category = category.replaceAll("[-_]"," ").toUpperCase();
    	if(category.length() < 5)
    		throw new IllegalArgumentException(MIN_CATEGORY_ERROR);
    	return updateSearchedResults(repo.findNominationsByYear(category, year));
    }
    public List<Movie> findNominationsBetweenYears(int start, int end) throws IllegalArgumentException {
    	if(start > end)
    		throw new IllegalArgumentException(RANGE_ERROR);
    	return updateSearchedResults(repo.findByYearNominatedBetween(start, end));
    }
    //possible method to use for custom searches
    public List<Movie> findByTitle(String title) throws IllegalArgumentException {
    	if(title.length() < 3)
    		throw new IllegalArgumentException(MIN_TITLE_ERROR);
    	//need to use _ since some movies use - in the title and the path would be messy
    	String searchTitle = title.replaceAll("_", " ");
    	//System.out.println(searchTitle);
    	return updateSearchedResults(repo.findByTitleContainingIgnoreCase(searchTitle));
    }
    public List<Movie> findByCategory(String category) throws IllegalArgumentException {
    	category = category.replaceAll("[-_]"," ").toUpperCase();
    	if(category.length() < 5)
    		throw new IllegalArgumentException(MIN_CATEGORY_ERROR);
    	return updateSearchedResults(repo.findByCategoryContainingIgnoreCase(category));
    }
    public List<Movie> findByTitleAndCategoryAndYear(String category, String title, int year) throws IllegalArgumentException {
    	category = category.replaceAll("[-_]"," ").toUpperCase();
    	if(category.length() < 5 && title.length() <3)
    		throw new IllegalArgumentException(MIN_CATEGORY_ERROR + " " +  MIN_TITLE_ERROR);
    	if(category.length() < 5)
    		throw new IllegalArgumentException(MIN_CATEGORY_ERROR);
    	if(title.length() < 3)
    		throw new IllegalArgumentException(MIN_TITLE_ERROR);
    	return updateSearchedResults(repo.findNominationsByTitleAndYear(category, title, year));
    }
    public List<Movie> findByCategoryBetweenYears(String category, int start, int end) throws IllegalArgumentException {
    	if(start > end)
    		throw new IllegalArgumentException(RANGE_ERROR);
    	category = category.replaceAll("[-_]"," ").toUpperCase();
    	if(category.length() < 5)
    		throw new IllegalArgumentException(MIN_CATEGORY_ERROR);
    	return updateSearchedResults(repo.findBetweenYearsAndByCategory(start, end, category));
    }
    public List<Movie> findWinnersByCategory(String category) throws IllegalArgumentException {
    	category = category.replaceAll("[-_]"," ").toUpperCase();
    	if(category.length() < 5)
    		throw new IllegalArgumentException(MIN_CATEGORY_ERROR);
    	return updateSearchedResults(repo.findByAwardStatusTrueAndCategoryContaining(category));
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
}
