package com.api.challenge.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.challenge.models.Genre;
import com.api.challenge.repository.GenreRepository;


@Service
public class GenreService {

	@Autowired
	private GenreRepository genreRepository;
	
	public Genre createGenre(Genre genre) {
		return genreRepository.save(genre);
	}
	public String delete(Long id) {
		genreRepository.deleteById(id);
		return new String("se ha borrado exitosamente el Continente." + id);
	}
	public Genre  update(Genre genre) {
		return genreRepository.save(genre);
	}
	
	public Genre findById(Long id) {
		return genreRepository.findById(id).orElse(null);
	}
	public List<Genre> findAll() {
		return genreRepository.findAll();
	}
}
