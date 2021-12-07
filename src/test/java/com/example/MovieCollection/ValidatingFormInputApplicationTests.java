package com.example.MovieCollection;

import static org.assertj.core.api.Assertions.assertThat;

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
}
