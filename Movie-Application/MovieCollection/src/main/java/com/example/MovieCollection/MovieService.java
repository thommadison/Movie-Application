package com.example.MovieCollection;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {
        private List<Movie> movies = new ArrayList<>(Arrays.asList(
                new Movie("1", "Toy Story 4", "Best Animated Feature Film", "2020"),
                new Movie("2", "How to Train your Dragon: The Hidden World", "Best Animated Feature", "2020"),
                new Movie("3", "I Lost my Body", "Best Animated Feature Film", "2020"),
                new Movie("4", "Klaus", "Best Animated Feature Film", "2020"),
                new Movie("5", "Missing Link", "Best Animated Feature Film", "2020")
        ));

        public List<Movie> getAllMovies(){
            return movies;
        }

        public Movie getMovie(String id){

            //iterator over list of topics and return id
           return  movies.stream().filter(t ->t.getId().equals(id)).findFirst().get();
        }


    public void addMovie(Movie movie) {
            movies.add(movie);
    }

    //loop to compare id to input id
    //if it matches, it will update that id
    public void updateMovie(String id, Movie movie ){
            for(int i = 0; i < movies.size(); i++){
                Movie m = movies.get(i);
                if(m.getId().equals(id)){
                    movies.set(i, movie);
                    return;
                }
            }
    }

    public void deleteMovie(String id) {
            movies.removeIf(t -> t.getId().equals(id));
    }
}
