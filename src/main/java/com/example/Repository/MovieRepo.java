package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.MovieCollection.Movie;

@Repository
public interface MovieRepo extends CrudRepository<Movie,Integer> {
	List<Movie> findByYearNominated(int year);
	@Query("FROM Movie WHERE category LIKE %?1% AND yearNominated=?2 AND award_status=true")
	List<Movie> findWinnerByYear(String award, int year);
	@Query("FROM Movie WHERE category LIKE %?1% AND yearNominated=?2")
	List<Movie> findNominationsByYear(String award, int year);
}
