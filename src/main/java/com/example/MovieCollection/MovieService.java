package com.example.MovieCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.MovieRepo;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

@Service
public class MovieService {
		@Autowired
		//initialized as null for some reason until I add PostConstruct on voidinitMovieCollection & leave constructor for MovieService empty
        private MovieRepo repo; 
		@Autowired
		private Movie temp;
		@Autowired
		private Movie returnMovie;
		@Autowired
		private List<Movie> movies = new ArrayList<Movie>();
		
		
        public MovieService() {
        	//initMovieCollection();
        }
        @PostConstruct
        public void initMovieCollection() {
        	String path = "the_oscar_award.csv";
    		String[] line;
    		try {
    			CSVReader csvReader = new CSVReader(new FileReader(path));
    			//reads line to skip category portion of file
    			line = csvReader.readNext();
    			int id = 0;
    			if(line!=null) {
    				while((line = csvReader.readNext()) != null) {
    					//0:yearReleased, 1:yearNominated, 3:category, 4:awardee, 5:title, 6:awardStatus
    					if(line[5] == null)
    						line[5] = "N/A";
    					//id, title, category, yearReleased, yearNominated, awardee, status
    					temp = new Movie(id,line[5],line[3],Integer.parseInt(line[0]),Integer.parseInt(line[1]),line[4],Boolean.parseBoolean(line[6]));
    					movies.add(temp);
    					repo.save(temp);
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
                temp = movies.get(i);
                if(temp.getId() == id){
                    movies.set(i, movie);
                    return;
                }
            }
    }

    public void deleteMovie(int id) {
            movies.removeIf(t -> t.getId() == id);
    }

	public List<Movie> getMoviesNominatedForYear(int year) {
    	List<Movie> list = new ArrayList<Movie>();
    	//retrieves ID of first Movie that was nominated for that year
    	int index = 1 + movies.stream().filter(t ->t.getYearNominated() == year).findFirst().get().getId();
    	//Movie temp;
    	while(index < movies.size() && (temp = movies.get(index)).getYearNominated() == year) {
			//Movie mov = new Movie();
			returnMovie.setTitle(temp.getTitle());
			returnMovie.setYearNominated(temp.getYearNominated());
			returnMovie.setYearReleased(temp.getYearReleased());
			returnMovie.setAwardee(temp.getAwardee());
			returnMovie.setAwardStatus(temp.isAwardStatus());
			returnMovie.setId(temp.getId());
			returnMovie.setCategory(temp.getCategory());
			list.add(returnMovie);
    		index++;
    	}
    	return list;
    }

    public List<Movie> getCategoryNominationsForYear(String category, int yearNominated) {
    	List<Movie> list = new ArrayList<Movie>();
    	//retrieves ID of first Movie that was nominated for that year
    	int index = 1 + movies.stream().filter(t ->t.getYearNominated() == yearNominated).findFirst().get().getId();
    	//Movie temp;
    	while(index < movies.size() && (temp = movies.get(index)).getYearNominated() == yearNominated) {
    		if(temp.getCategory().contains(category)) {
    			//Movie mov = new Movie();
    			returnMovie.setTitle(temp.getTitle());
    			returnMovie.setYearNominated(temp.getYearNominated());
    			returnMovie.setYearReleased(temp.getYearReleased());
    			returnMovie.setAwardee(temp.getAwardee());
    			returnMovie.setAwardStatus(temp.isAwardStatus());
    			returnMovie.setId(temp.getId());
    			returnMovie.setCategory(temp.getCategory());
    			list.add(returnMovie);
    		}
    		index++;
    	}
    	return list;
    }

    public Movie getWinner(String category, int yearNominated) {
    	return movies.stream().filter(t ->t.getYearNominated() == yearNominated && t.getCategory().contains(category) && t.isAwardStatus() == true).findFirst().get();
    }
}
