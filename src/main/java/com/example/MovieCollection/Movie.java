package com.example.MovieCollection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private String title;
    private String category;
    private int yearReleased;
    private int yearNominated;
    @Column(columnDefinition = "TEXT") 
    private String awardee;
    private boolean awardStatus;
    private String link;

    //constructors
    //constructor using data from .csv file
    public Movie(int id, String title, String category, int yearReleased, int yearNominated, String awardee, boolean awardStatus) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.yearReleased = yearReleased;
        this.yearNominated = yearNominated;
        this.awardee = awardee;
        this.awardStatus = awardStatus;
    }
    public Movie() {

    }
    
    //getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


	public int getYearNominated() {
		return yearNominated;
	}

	public void setYearNominated(int yearNominated) {
		this.yearNominated = yearNominated;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getYearReleased() {
		return yearReleased;
	}

	public void setYearReleased(int yearReleased) {
		this.yearReleased = yearReleased;
	}

	public boolean isAwardStatus() {
		return awardStatus;
	}

	public void setAwardStatus(boolean awardStatus) {
		this.awardStatus = awardStatus;
	}

	public String getAwardee() {
		return awardee;
	}

	public void setAwardee(String awardee) {
		this.awardee = awardee;
	}
    
	public String toString() {
		return "ID: " + getId() + "\tTitle: " + getTitle()
				+ "\tCategory: " + getCategory() + "\tReleased: "
				+ getYearReleased() + "\tNominated: " + getYearNominated()
				+ "\tAwardee: " + getAwardee() + "\tStatus: " + isAwardStatus();
	}
}
