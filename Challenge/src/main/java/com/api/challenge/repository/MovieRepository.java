package com.api.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.challenge.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

	List<Movie> findByTitle(String title);
	List<Movie> findByGenreId(Long id);
	List<Movie> findByOrderByCreationAsc();
	List<Movie> findByOrderByCreationDesc();
}

