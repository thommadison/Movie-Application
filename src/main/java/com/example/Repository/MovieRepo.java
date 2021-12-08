package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.MovieCollection.Movie;

@Repository
public interface MovieRepo extends CrudRepository<Movie,Integer> {
	@Query("FROM Movie WHERE yearNominated=?1")
	List<Movie> findByYear(int year);
	@Query("FROM Movie WHERE category LIKE %?1% AND yearNominated=?2 AND award_status=true")
	List<Movie> findWinnerByYear(String award, int year);
	@Query("FROM Movie WHERE category LIKE %?1% AND yearNominated=?2")
	List<Movie> findNominationsByYear(String award, int year);
	@Query("FROM Movie WHERE category LIKE %?1% AND title LIKE %?2% AND yearNominated=?3")
	List<Movie> findNominationsByTitleAndYear(String award, String title, int year);
	Movie findById(int id);
	List<Movie> findByTitleContainingIgnoreCase(String title);
	List<Movie> findByCategoryContainingIgnoreCase(String category);
	List<Movie> findByYearNominatedBetween(int start, int end);
	@Query("FROM Movie WHERE category LIKE %?3% AND yearNominated BETWEEN ?1 AND ?2")
	List<Movie> findBetweenYearsAndByCategory(int start, int end, String category);
	//@Query("FROM Movie WHERE category LIKE %?1% AND award_status=true")
	List<Movie> findByAwardStatusTrueAndCategoryContaining(String category);
}
