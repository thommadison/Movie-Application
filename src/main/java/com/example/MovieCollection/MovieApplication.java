package com.example.MovieCollection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication
//@ComponentScan({"com.example.MovieCollection.MovieService","com.example.MovieCollection.Movie","com.example.MovieCollection.controller","com.example.MovieCollection.MovieService"})
//@EntityScan("com.example.MovieCollection.Movie")
@EnableJpaRepositories("com.example.Repository")
public class MovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}

}
