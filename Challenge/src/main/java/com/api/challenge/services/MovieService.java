package com.api.challenge.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.challenge.models.Character;
import com.api.challenge.models.Movie;
import com.api.challenge.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private CharacterService charService;
	
	public Movie createMovie(Movie movie) {
		Movie newMovie= new Movie();
		newMovie.setTitle(movie.getTitle());
		newMovie.setPicture(movie.getPicture());
		newMovie.setCreation(movie.getCreation());
		newMovie.setQualification(movie.getQualification());
		newMovie.setGenre(movie.getGenre());
		newMovie.getCharacters()
	                .addAll( movie.getCharacters()
	                        .stream()
	                        .map(c ->{
	                                Character cc = charService.findById(c.getId());
	                                if(cc != null) {
	                                cc.getMovies().add(newMovie);
	                               
	                                } 
	                                return cc;
	                        }).collect(Collectors.toList()));
		return movieRepository.save(newMovie);
	}

	
	
	public Movie findById(Long id) {
		return movieRepository.findById(id).orElse(null);
	}
	
	public String delete(Long id) {
		movieRepository.deleteById(id);
		if(movieRepository.findById(id)!=null) {
		return new String("No se pudo borrar la película" + id);
		} else {
			return new String("se ha borrado con éxito la película" + id);
		}
	}
	public Movie update(Movie movie) {
		Movie newMovie= new Movie();
		newMovie.setId(movie.getId());
		newMovie.setTitle(movie.getTitle());
		newMovie.setPicture(movie.getPicture());
		newMovie.setCreation(movie.getCreation());
		newMovie.setQualification(movie.getQualification());
		newMovie.setGenre(movie.getGenre());
		newMovie.getCharacters()
	                .addAll( movie.getCharacters()
	                        .stream()
	                        .map(c ->{
	                                Character cc = charService.findById(c.getId());
	                                if(cc != null) {
	                                cc.getMovies().add(newMovie);
	                               
	                                } 
	                                return cc;
	                        }).collect(Collectors.toList()));
		return movieRepository.save(newMovie);
	}
	public List<Movie> findAll() {
		return movieRepository.findAll();
	}
	public List<Movie> getMovieByTitle(String title) {
		return movieRepository.findByTitle(title);
	}
	public List<Movie> getMovieByGenreId(Long id) {
		return movieRepository.findByGenreId(id);
	}
	public List<Movie> getMovieOrderByCreationAsc() {
		return movieRepository.findByOrderByCreationAsc();
	}
	public List<Movie> getMovieOrderByCreationDesc() {
		return movieRepository.findByOrderByCreationDesc();
	}
	
}
