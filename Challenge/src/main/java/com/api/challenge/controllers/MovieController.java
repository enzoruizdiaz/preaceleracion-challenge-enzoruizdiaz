package com.api.challenge.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.challenge.dto.MovieDto;
import com.api.challenge.dto.MovieDtoList;
import com.api.challenge.models.Movie;
import com.api.challenge.services.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	private ModelMapper mapper = new ModelMapper();
	
	@GetMapping
	public ResponseEntity<?> ListarMovies() {
		List<Movie> listMovies = movieService.findAll();
		if (listMovies.isEmpty()) {
			return new ResponseEntity<>(new String("No se encontraron películas."), HttpStatus.BAD_REQUEST);
		} else {
			List<MovieDtoList> listaDtoMovie = new ArrayList<>();
			for (Movie m : listMovies) {
				MovieDtoList dtoMovieAux = new MovieDtoList();
				dtoMovieAux.setTitle(m.getTitle());
				dtoMovieAux.setPicture(m.getPicture());
				dtoMovieAux.setCreation(m.getCreation());
				listaDtoMovie.add(dtoMovieAux);
			}
			return new ResponseEntity<>(listaDtoMovie, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getId(@PathVariable("id") Long id) {
		Movie auxMovie = movieService.findById(id);
		if (auxMovie == null) {
			return new ResponseEntity<>(new String("El id ingresado no existe."), HttpStatus.BAD_REQUEST);

		} else {
		
			MovieDto movieDto = new MovieDto();
			movieDto = mapToDto(auxMovie);
			return new ResponseEntity<>(movieDto, HttpStatus.OK);
		}
	}

	@PostMapping
	public ResponseEntity<?> createMovies(@RequestBody MovieDto movieDto) {
		
		
		Movie movie = mapToEntity(movieDto);
		movie = movieService.createMovie(movie);
		MovieDto moviesDto = mapToDto(movie);
		
		return new ResponseEntity<>(moviesDto,HttpStatus.CREATED);
	
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Movie movie) {
		if (movieService.findById(movie.getId()) == null) {
			return new ResponseEntity<>(new String("El id es incorrecto."), HttpStatus.BAD_REQUEST);
		} else {
			movieService.update(movie);
			return new ResponseEntity<>(new String("Se ha actualizado con éxito"), HttpStatus.OK);

		}

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if(movieService.findById(id)==null){
			return new ResponseEntity<>(new String("El id es incorrecto."),HttpStatus.BAD_REQUEST);
		}
		else {
			movieService.delete(id);
		return new ResponseEntity<>(new String("Se ha borrado con éxito."),HttpStatus.OK);
		}
	}
	
	@GetMapping("/filter")
	public ResponseEntity<?> filter(@RequestParam(name="name",required=false)String nombre,
									@RequestParam(name="genre",required=false)Long idGenero,
									@RequestParam(name="order",required=false)String order){
		List<MovieDto> movieListDto = new ArrayList<>();
		List<Movie> movieList = new ArrayList<>();
		if(nombre!=null) {
			movieList = movieService.getMovieByTitle(nombre);
			movieListDto = mapListToDto(movieList);
			return new  ResponseEntity<>(movieListDto,HttpStatus.OK);
		} else if(idGenero !=null) {
			movieList = movieService.getMovieByGenreId(idGenero);
			movieListDto = mapListToDto(movieList);
			return new  ResponseEntity<>(movieListDto,HttpStatus.OK);
			
		}else if(order!=null) {
			if(order.equals("DESC")) {
			movieList = movieService.getMovieOrderByCreationDesc();
			movieListDto = mapListToDto(movieList);
			return new  ResponseEntity<>(movieListDto,HttpStatus.OK);
			
			} else if(order.equals("ASC")) {
			movieList = movieService.getMovieOrderByCreationAsc();
			movieListDto = mapListToDto(movieList);
			return new  ResponseEntity<>(movieListDto,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new String("el parámetro deber ser ASC o DESC"),HttpStatus.BAD_REQUEST);
		}}
		else {
			return null;//poner el otro get
		}
	}
	
	private MovieDto mapToDto(Movie m) {
		MovieDto movieDto =mapper.map(m,MovieDto.class);
		
		return movieDto;
	}
	
	private Movie mapToEntity(MovieDto movieDto) {
		Movie movie =mapper.map(movieDto,Movie.class);
		return movie;
	}
	
	private List<MovieDto> mapListToDto(List<Movie> list){
		List<MovieDto> auxList = new ArrayList<>();
		for(Movie m : list) {
			MovieDto mm = new MovieDto();
			mm =mapToDto(m);
			auxList.add(mm);
		}
		return auxList;
	}
	
	
	
	
	

}
