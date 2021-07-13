package com.api.challenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.challenge.models.Genre;
import com.api.challenge.services.GenreService;

@RestController
@RequestMapping("/genre")
public class GenreController {
	
	@Autowired
	private GenreService genreService;
	
	@GetMapping
	public ResponseEntity<?> listContinent() {
		  List<Genre> listGenre = genreService.findAll();
	        if (listGenre.isEmpty()) {
	            return new ResponseEntity<>(new String("No se encontraron Géneros."), HttpStatus.BAD_REQUEST);
	        }else {
	        	return new ResponseEntity<>(listGenre, HttpStatus.OK);
	        }
	}
	        
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getId(@PathVariable("id") Long id) {
		Genre auxGenre = genreService.findById(id);
		if (auxGenre == null) {
			return new ResponseEntity<>(new String("El id ingresado no existe."), HttpStatus.BAD_REQUEST);

		} else {
			return new ResponseEntity<>(auxGenre, HttpStatus.OK);
		}

	}
	
	@PostMapping
	public ResponseEntity<?> createGenre(@RequestBody Genre genre) {
		if (genre.getName().isBlank()) {
			return new ResponseEntity<>(new String("Debe contener un nombre."), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(genreService.createGenre(genre), HttpStatus.CREATED);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Genre genre){
		if(genreService.findById(genre.getId())==null){
			return new ResponseEntity<>(new String("El id es incorrecto."),HttpStatus.BAD_REQUEST);
		} else {
			genreService.update(genre);
			return new ResponseEntity<>(new String("Se ha actualizado con éxito"),HttpStatus.OK);
					
		}
      
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if(genreService.findById(id)==null){
			return new ResponseEntity<>(new String("El id es incorrecto."),HttpStatus.BAD_REQUEST);
		}
		else {
			genreService.delete(id);
		return new ResponseEntity<>(new String("Se ha borrado con éxito."),HttpStatus.OK);
		}
	}
	
	
	

}
