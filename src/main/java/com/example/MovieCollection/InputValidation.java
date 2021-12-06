package com.example.MovieCollection;

public class InputValidation {
	private static final String MIN_CATEGORY_ERROR = "Category cannot be less than 5 characters.";
	private static final String MIN_TITLE_ERROR = "Title cannot be less than 3 characters.";
	private static final String RANGE_ERROR = "Starting year cannot be less than ending year";
	private static final String YEAR_NOMINATED_ERROR = "Input nominated year must be between 1928 to 2020";
	private static final String ID_ERROR = "ID cannot be less than 1";
	private static final int MIN_YEAR_NOMINATED = 1928;
	private static final int MAX_YEAR_NOMINATED = 2020;
	public static String categoryLengthValidation(String category) throws IllegalArgumentException {
		category = category.replaceAll("[-_]"," ").toUpperCase();
		if(category.length() < 5)
    		throw new IllegalArgumentException(MIN_CATEGORY_ERROR);
		return category;
	}
	public static String titleLengthValidation(String title) throws IllegalArgumentException {
		if(title.length() < 3)
    		throw new IllegalArgumentException(MIN_TITLE_ERROR);
		return title.replaceAll("_", " ");
	}
	public static void dateRangeValidation(int start, int end) throws IllegalArgumentException {
		if(start > end)
    		throw new IllegalArgumentException(RANGE_ERROR);
		yearNominatedValidation(start);
		yearNominatedValidation(end);
	}
	public static void yearNominatedValidation(int year) throws IllegalArgumentException {
		if(year < MIN_YEAR_NOMINATED || year > MAX_YEAR_NOMINATED)
			throw new IllegalArgumentException(YEAR_NOMINATED_ERROR);
	}
	public static void iDValidation(int id) throws IllegalArgumentException {
		if(id < 1)
    		throw new IllegalArgumentException(ID_ERROR);
	}
}
