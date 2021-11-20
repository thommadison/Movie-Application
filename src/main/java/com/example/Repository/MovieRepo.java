package com.example.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.MovieCollection.Movie;

@Repository
public interface MovieRepo extends CrudRepository<Movie,Integer> {

}
