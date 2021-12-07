package com.example.MovieCollection;

public class InputValidation {
	private static final String MIN_CATEGORY_ERROR = "Category cannot be less than 5 characters.";
	private static final String MIN_TITLE_ERROR = "Title cannot be less than 3 characters.";
	private static final String RANGE_ERROR = "Starting year cannot be less than ending year";
	private static final String YEAR_OUTSIDE_RANGE_ERROR = "Input nominated year must be between 1928 to 2020";
	private static final String MISMATCH_YEAR_ERROR = "Please enter an integer year between 1928 to 2020";
	private static final String ID_ERROR = "ID cannot be less than 1";
	private static final String MISMATCH_ID_ERROR = "ID must be an integer";
	private static final int MIN_YEAR_NOMINATED = 1928;
	private static final int MAX_YEAR_NOMINATED = 2020;
	//removes - & _ and ensures category length isn't less than 5 characters
	public static String categoryLengthValidation(String category) throws IllegalArgumentException {
		category = category.replaceAll("[-_]"," ").toUpperCase();
		if(category.length() < 5)
    		throw new IllegalArgumentException(MIN_CATEGORY_ERROR);
		return category;
	}
	//ensures title isn't less than 3 characters (replaces all _ with " " in case we ever used that for the path)
	public static String titleLengthValidation(String title) throws IllegalArgumentException {
		if(title.length() < 3)
    		throw new IllegalArgumentException(MIN_TITLE_ERROR);
		return title.replaceAll("_", " ");
	}
	public static void dateRangeValidation(int start, int end) throws IllegalArgumentException {
		if(start > end)
    		throw new IllegalArgumentException(RANGE_ERROR);
	}
	//returns int after verifying that input year is an integer & within 1980 to 2020
	public static int yearNominatedValidation(String yearInput) throws IllegalArgumentException {
		int year = yearTypeValidation(yearInput);
		if(year < MIN_YEAR_NOMINATED || year > MAX_YEAR_NOMINATED)
			throw new IllegalArgumentException(YEAR_OUTSIDE_RANGE_ERROR);
		return year;
	}
	//ensures ID is
	public static int iDValidation(String idInput) throws IllegalArgumentException {
		try {
			int id = Integer.parseInt(idInput);
			if(id < 1)
				throw new IllegalArgumentException(ID_ERROR);
			return id;
		} catch (Exception e) {
			throw new IllegalArgumentException(MISMATCH_ID_ERROR);
		}
	}
	//checks to see if inputted year is an integer
	public static int yearTypeValidation(String year) throws IllegalArgumentException {
		try {
			return Integer.parseInt(year);
		} catch(Exception e) {
			throw new IllegalArgumentException(MISMATCH_YEAR_ERROR);
		}
	}
}
