package com.example.MovieCollection;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {
        private List<Movie> movies = new ArrayList<Movie>();
        public MovieService() {
        	initMovieCollection();
        }
        public void initMovieCollection() {
        	String path = "the_oscar_award.csv";
    		String[] line;
    		try {
    			CSVReader csvReader = new CSVReader(new FileReader(path));
    			line = csvReader.readNext();
    			int id = 0;
    			if(line!=null) {
    				while((line = csvReader.readNext()) != null) {
    					//0:yearReleased, 1:yearNominated, 3:category, 4:awardee, 5:title, 6:awardStatus
    					if(line[5] == null)
    						line[5] = "N/A";
    					//id, title, category, yearReleased, yearNominated, awardee, status
    					Movie newMovie = new Movie(id,line[5],line[3],Integer.parseInt(line[0]),Integer.parseInt(line[1]),line[4],Boolean.parseBoolean(line[6]));
    					movies.add(newMovie);
    					id++;
    				}
    			}
    		} catch(FileNotFoundException e) {
    			e.printStackTrace();
    		} catch(IOException e) {
    			e.printStackTrace();
    		}
        }
        //don't do this unless you want to break something
        public List<Movie> getAllMovies(){
            return movies;
        }

        public Movie getMovie(int id){

            //iterator over list of topics and return id
           return  movies.stream().filter(t ->t.getId() == id).findFirst().get();
        }


    public void addMovie(Movie movie) {
            movies.add(movie);
    }

    //loop to compare id to input id
    //if it matches, it will update that id
    public void updateMovie(int id, Movie movie ){
            for(int i = 0; i < movies.size(); i++){
                Movie m = movies.get(i);
                if(m.getId() == id){
                    movies.set(i, movie);
                    return;
                }
            }
    }

    public void deleteMovie(int id) {
            movies.removeIf(t -> t.getId() == id);
    }
    
    public List<Movie> getCategoryNominationsForYear(String category, int yearNominated) {
    	List<Movie> list = new ArrayList<Movie>();
    	int index = 1 + movies.stream().filter(t ->t.getYearNominated() == yearNominated).findFirst().get().getId();
    	Movie temp;
    	while(index < movies.size() && (temp = movies.get(index)).getYearNominated() == yearNominated) {
    		if(temp.getCategory().contains(category)) {
    			Movie mov = new Movie();
    			mov.setTitle(temp.getTitle());
    			mov.setYearNominated(temp.getYearNominated());
    			mov.setYearReleased(temp.getYearReleased());
    			mov.setAwardee(temp.getAwardee());
    			mov.setAwardStatus(temp.isAwardStatus());
    			mov.setId(temp.getId());
    			mov.setCategory(temp.getCategory());
    			list.add(mov);
    		}
    		index++;
    	}
    	return list;
    }
    public Movie getWinner(String category, int yearNominated) {
    	return movies.stream().filter(t ->t.getYearNominated() == yearNominated && t.getCategory().contains(category) && t.isAwardStatus() == true).findFirst().get();
    }
}
