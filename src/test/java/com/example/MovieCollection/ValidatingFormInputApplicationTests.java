package com.example.MovieCollection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValidatingFormInputApplicationTests {

	@Autowired
	private MovieService testService;
	private String args;

	@Test
	void contextLoads() throws Exception {
		assertThat(testService).isNotNull();
	}

	@Test
	public void Movie() {
		Movie testMovie;
		String title = "1917";
		String category = "WRITING (ORIGINAL SCREENPLAY)";
		int yearReleased = 2019;
		int yearNominated = 2020;
		String awardee = "Written by Sam Mendes &amp; Krysty Wilson-Cairns";
		boolean awardStatus = false;
		testMovie = new Movie(title, category, yearReleased, yearNominated, awardee, awardStatus);
		testMovie.setId(0);
		assertThat(testMovie).isEqualTo(testMovie);
	}
	
	@Test
	public void testGetSetTitle() {
		Movie testMovie;
		String title = "1917";
		String category = "WRITING (ORIGINAL SCREENPLAY)";
		int yearReleased = 2019;
		int yearNominated = 2020;
		String awardee = "Written by Sam Mendes &amp; Krysty Wilson-Cairns";
		boolean awardStatus = false;
		// public Movie(String title, String category, int yearReleased, int yearNominated, String awardee, boolean awardStatus)
		testMovie = new Movie(title, category, yearReleased, yearNominated, awardee, awardStatus);
		testMovie.setTitle(title);
		assertThat(testMovie.getTitle()).contains(title);
	}
	
	@Test
	public void testGetSetCategory() {
		Movie testMovie;
		String title = "1917";
		String category = "WRITING (ORIGINAL SCREENPLAY)";
		int yearReleased = 2019;
		int yearNominated = 2020;
		String awardee = "Written by Sam Mendes &amp; Krysty Wilson-Cairns";
		boolean awardStatus = false;
		// public Movie(String title, String category, int yearReleased, int yearNominated, String awardee, boolean awardStatus)
		testMovie = new Movie(title, category, yearReleased, yearNominated, awardee, awardStatus);
		testMovie.setCategory(category);
		assertThat(testMovie.getCategory()).contains(category);
	}
	
	@Test
	public void testGetSetYearReleased() {
		Movie testMovie;
		String title = "1917";
		String category = "WRITING (ORIGINAL SCREENPLAY)";
		int yearReleased = 2019;
		int yearNominated = 2020;
		String awardee = "Written by Sam Mendes &amp; Krysty Wilson-Cairns";
		boolean awardStatus = false;
		// public Movie(String title, String category, int yearReleased, int yearNominated, String awardee, boolean awardStatus)
		testMovie = new Movie(title, category, yearReleased, yearNominated, awardee, awardStatus);
		testMovie.setYearReleased(yearReleased);
		assertThat(testMovie.getYearReleased()).isEqualTo(yearReleased);
	}
	
	@Test
	public void testGetSetYearNominated() {
		Movie testMovie;
		String title = "1917";
		String category = "WRITING (ORIGINAL SCREENPLAY)";
		int yearReleased = 2019;
		int yearNominated = 2020;
		String awardee = "Written by Sam Mendes &amp; Krysty Wilson-Cairns";
		boolean awardStatus = false;
		// public Movie(String title, String category, int yearReleased, int yearNominated, String awardee, boolean awardStatus)
		testMovie = new Movie(title, category, yearReleased, yearNominated, awardee, awardStatus);
		testMovie.setYearNominated(yearNominated);
		assertThat(testMovie.getYearNominated()).isEqualTo(yearNominated);
	}
	
	@Test
	public void testGetSetAwardee() {
		Movie testMovie;
		String title = "1917";
		String category = "WRITING (ORIGINAL SCREENPLAY)";
		int yearReleased = 2019;
		int yearNominated = 2020;
		String awardee = "Written by Sam Mendes &amp; Krysty Wilson-Cairns";
		boolean awardStatus = false;
		// public Movie(String title, String category, int yearReleased, int yearNominated, String awardee, boolean awardStatus)
		testMovie = new Movie(title, category, yearReleased, yearNominated, awardee, awardStatus);
		testMovie.setAwardee(awardee);
		assertThat(testMovie.getAwardee()).contains(awardee);
	}
	
	@Test
	public void testGetSetIsAwardStatus() {
		Movie testMovie;
		String title = "1917";
		String category = "WRITING (ORIGINAL SCREENPLAY)";
		int yearReleased = 2019;
		int yearNominated = 2020;
		String awardee = "Written by Sam Mendes &amp; Krysty Wilson-Cairns";
		boolean awardStatus = false;
		// public Movie(String title, String category, int yearReleased, int yearNominated, String awardee, boolean awardStatus)
		testMovie = new Movie(title, category, yearReleased, yearNominated, awardee, awardStatus);
		testMovie.setAwardStatus(awardStatus);
		assertThat(testMovie.isAwardStatus()).isFalse();
	}
	
	@Test
	public void testGetSetImageLink() {
		Movie testMovie;
		String title = "1917";
		String category = "WRITING (ORIGINAL SCREENPLAY)";
		int yearReleased = 2019;
		int yearNominated = 2020;
		String awardee = "Written by Sam Mendes &amp; Krysty Wilson-Cairns";
		boolean awardStatus = false;
		// public Movie(String title, String category, int yearReleased, int yearNominated, String awardee, boolean awardStatus)
		testMovie = new Movie(title, category, yearReleased, yearNominated, awardee, awardStatus);
		testMovie.setImageLink(null);
		assertThat(testMovie.getImageLink()).isNull();
	}
	
	@Test
	public void testGetSetLink() {
		Movie testMovie;
		String title = "1917";
		String category = "WRITING (ORIGINAL SCREENPLAY)";
		int yearReleased = 2019;
		int yearNominated = 2020;
		String awardee = "Written by Sam Mendes &amp; Krysty Wilson-Cairns";
		boolean awardStatus = false;
		// public Movie(String title, String category, int yearReleased, int yearNominated, String awardee, boolean awardStatus)
		testMovie = new Movie(title, category, yearReleased, yearNominated, awardee, awardStatus);
		testMovie.setLink(null);
		assertThat(testMovie.getLink()).isNull();
	}
	
	@Test
	public void testGetSetPlot() {
		Movie testMovie;
		String title = "1917";
		String category = "WRITING (ORIGINAL SCREENPLAY)";
		int yearReleased = 2019;
		int yearNominated = 2020;
		String awardee = "Written by Sam Mendes &amp; Krysty Wilson-Cairns";
		boolean awardStatus = false;
		// public Movie(String title, String category, int yearReleased, int yearNominated, String awardee, boolean awardStatus)
		testMovie = new Movie(title, category, yearReleased, yearNominated, awardee, awardStatus);
		testMovie.setPlot(null);
		assertThat(testMovie.getPlot()).isNull();
	}
	
	@Test
	public void testGetSetTmdbId() {
		Movie testMovie;
		String title = "1917";
		String category = "WRITING (ORIGINAL SCREENPLAY)";
		int yearReleased = 2019;
		int yearNominated = 2020;
		String awardee = "Written by Sam Mendes &amp; Krysty Wilson-Cairns";
		boolean awardStatus = false;
		// public Movie(String title, String category, int yearReleased, int yearNominated, String awardee, boolean awardStatus)
		testMovie = new Movie(title, category, yearReleased, yearNominated, awardee, awardStatus);
		testMovie.setTmdbId(0);
		assertThat(testMovie.getTmdbId()).isEqualTo(0);
	}
	
	@Test
	public void testToString() {
		Movie testMovie;
		String title = "1917";
		String category = "WRITING (ORIGINAL SCREENPLAY)";
		int yearReleased = 2019;
		int yearNominated = 2020;
		String awardee = "Written by Sam Mendes &amp; Krysty Wilson-Cairns";
		boolean awardStatus = false;
		// public Movie(String title, String category, int yearReleased, int yearNominated, String awardee, boolean awardStatus)
		testMovie = new Movie(title, category, yearReleased, yearNominated, awardee, awardStatus);
		assertThat(testMovie.toString()).contains("1917");
	}
	
	@Test
	public void main() {
		MovieApplication.main(new String[] {});
	}
	
	@Test
	public void invalidIntType() {
		String year = "a";
		assertThrows(IllegalArgumentException.class, () -> {InputValidation.yearTypeValidation(year);});
	}
	@Test
	public void validIntType() {
		String yearStr = "1992";
		int year = 1992;
		assertThat(InputValidation.yearTypeValidation(yearStr) == year);
	}
	@Test
	public void invalidNominatedYear() {
		String yearLow = "1979";
		String yearHigh = "2021";
		String year = "a";
		assertThrows(IllegalArgumentException.class, () -> {InputValidation.yearNominatedValidation(yearLow);});
		assertThrows(IllegalArgumentException.class, () -> {InputValidation.yearNominatedValidation(yearHigh);});
		assertThrows(IllegalArgumentException.class, () -> {InputValidation.yearNominatedValidation(year);});
	}
	@Test
	public void validNominatedYear() {
		String yearStr = "1980";
		assertThat(InputValidation.yearNominatedValidation(yearStr) == 1980);
	}
	@Test
	public void invalidDateRange() {
		int start = 2020;
		int end = 1990;
		assertThrows(IllegalArgumentException.class, () -> {InputValidation.dateRangeValidation(start, end);});
	}
	@Test
	public void validDateRange() {
		int start = 1980;
		int end = 2020;
		assertThat(InputValidation.dateRangeValidation(start, end) == true);
	}
	@Test
	public void invalidTitleLength() {
		String title = "a";
		assertThrows(IllegalArgumentException.class, () -> {InputValidation.titleLengthValidation(title);});
	}
	@Test
	public void validTitleLength() {
		String title = "abc";
		assertThat(InputValidation.titleLengthValidation(title).contains(title));
	}
	@Test
	public void invalidCategoryLength() {
		String category = "acto";
		String cat2 = "a   ";
		assertThrows(IllegalArgumentException.class, () -> {InputValidation.categoryLengthValidation(category);});
		assertThrows(IllegalArgumentException.class, () -> {InputValidation.categoryLengthValidation(cat2);});
	}
	@Test
	public void validCategoryLength() {
		String category = "actor";
		assertThat(InputValidation.categoryLengthValidation(category).equals("ACTOR"));
	}
	@Test
	public void invalidId() {
		String idStr = "a";
		String idLow = "0";
		assertThrows(IllegalArgumentException.class, () -> {InputValidation.iDValidation(idStr);});
		assertThrows(IllegalArgumentException.class, () -> {InputValidation.iDValidation(idLow);});
	}
	@Test
	public void validId() {
		String id = "1";
		assertThat(InputValidation.iDValidation(id) == 1);
	}
}
