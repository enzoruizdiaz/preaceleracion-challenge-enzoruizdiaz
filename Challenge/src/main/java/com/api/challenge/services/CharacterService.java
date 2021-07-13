package com.api.challenge.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.challenge.models.Character;
import com.api.challenge.models.Movie;
import com.api.challenge.repository.CharacterRepository;

@Service
public class CharacterService {

	@Autowired
	private CharacterRepository charRepository;
	@Autowired
	private MovieService movieService;
	
	public Character createCharacter(Character character) {
		Character newChar= new Character();
		newChar.setName(character.getName());
		newChar.setPicture(character.getPicture());
		newChar.setAge(character.getAge());
		newChar.setWeight(character.getWeight());
		newChar.setStory(character.getStory());
		newChar.getMovies()
	                .addAll( character.getMovies()
	                        .stream()
	                        .map(m ->{
	                                Movie mm = movieService.findById(m.getId());
	                                if(mm != null) {
	                                mm.getCharacters().add(newChar);
	                               
	                                } 
	                                return mm;
	                      
	                               
	                           
	                        }).collect(Collectors.toList()));
		return charRepository.save(newChar);
	}
	
	public String delete(Long id) {
		charRepository.deleteById(id);
		return new String("se ha borrado exitosamente el personaje" + id );
	}
	public Character update(Character character) {
		Character newChar= new Character();
		newChar.setId(character.getId());
		newChar.setName(character.getName());
		newChar.setPicture(character.getPicture());
		newChar.setAge(character.getAge());
		newChar.setWeight(character.getWeight());
		newChar.setStory(character.getStory());
		newChar.getMovies()
	                .addAll( character.getMovies()
	                        .stream()
	                        .map(m ->{
	                                Movie mm = movieService.findById(m.getId());
	                                if(mm != null) {
	                                mm.getCharacters().add(newChar);
	                               
	                                } 
	                                return mm;
	                      
	                               
	                           
	                        }).collect(Collectors.toList()));
		return charRepository.save(newChar);
	}
	
	public Character findById(Long id) {
		return charRepository.findById(id).orElse(null);
	}
	public List<Character> findAll() {
		return charRepository.findAll();
	}
	public List<Character> getCharacterByName(String name) {
		return charRepository.findByName(name);
	}
	public List<Character> getCharacterByAge(String age) {
		return charRepository.findByAge(age);
	}
	public List<Character> getCharacterByWeight(Double weight) {
		return charRepository.findByWeight(weight);
	}
	public List<Character> getCharacterByMovies(Long id) {
		return charRepository.findByMoviesId(id);
	}
}
