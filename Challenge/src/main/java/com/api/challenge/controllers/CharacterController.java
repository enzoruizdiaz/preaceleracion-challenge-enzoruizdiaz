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

import com.api.challenge.services.CharacterService;
import com.api.challenge.dto.CharacterDto;
import com.api.challenge.dto.CharacterDtoList;
import com.api.challenge.models.Character;


@RestController
@RequestMapping("/characters")
public class CharacterController {
	
	@Autowired
	private CharacterService charService;
	
	private ModelMapper mapper = new ModelMapper();
	
	@GetMapping
	//metodo de filtros y devuelve los characters con datos de nombre e imagen
	public ResponseEntity<?> characters(@RequestParam(name="name",required=false)  String nombre,
									@RequestParam(name="age",required=false) String edad,
									@RequestParam(name="weight",required=false)Double peso,
									@RequestParam(name="movies",required=false)Long idMovie){
		
		List<CharacterDto> charListDto = new ArrayList<>();
		List<Character> charList = new ArrayList<>();
		if(nombre!=null) {
			charList = charService.getCharacterByName(nombre);
			charListDto = mapListToDto(charList);
			return new ResponseEntity<>(charListDto,HttpStatus.OK);
		} 
		else if(edad!=null) {
			charList = charService.getCharacterByAge(edad);
			charListDto = mapListToDto(charList);
			return new ResponseEntity<>(charListDto,HttpStatus.OK);
		} else if(peso!=null) {
			charList = charService.getCharacterByWeight(peso);
			charListDto = mapListToDto(charList);
			return new ResponseEntity<>(charListDto,HttpStatus.OK);

		} else if(idMovie!=null) {
			
			
			charList = charService.getCharacterByMovies(idMovie);
			charListDto = mapListToDto(charList);
			
			return new ResponseEntity<>(charListDto,HttpStatus.OK);

		}else {
			List<Character> listChar = charService.findAll();
			if (listChar.isEmpty()) {
				return new ResponseEntity<>(new String("No se encontraron personajes."), HttpStatus.BAD_REQUEST);
			} else {
				List<CharacterDtoList> listaDtoChar = new ArrayList<>();
				for (Character c : listChar) {
					CharacterDtoList dtoCharAux = new CharacterDtoList();
					dtoCharAux.setName(c.getName());
					dtoCharAux.setPicture(c.getPicture());
					listaDtoChar.add(dtoCharAux);
				}
				return new ResponseEntity<>(listaDtoChar, HttpStatus.OK);
			}
		}
		
		
	}
	
	//buscar por id
	@GetMapping("/{id}")
	public ResponseEntity<?> getId(@PathVariable("id") Long id) {
		Character auxChar = charService.findById(id);
		if (auxChar == null) {
			return new ResponseEntity<>(new String("El id ingresado no existe."), HttpStatus.BAD_REQUEST);

		} else {
		
			CharacterDto charDto = new CharacterDto();
			charDto = mapToDto(auxChar);
			return new ResponseEntity<>(charDto, HttpStatus.OK);
		}
	}
	//metodo para crear 
	@PostMapping
	public ResponseEntity<?> createCharacter(@RequestBody CharacterDto charDto) {
		
		Character character = mapToEntity(charDto);
		character = charService.createCharacter(character);
		CharacterDto characterDto = mapToDto(character);
		
		return new ResponseEntity<>(characterDto,HttpStatus.CREATED);
	
	}
	//metodo para actualizar un personaje
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Character character) {
		if (charService.findById(character.getId()) == null) {
			return new ResponseEntity<>(new String("El id es incorrecto."), HttpStatus.BAD_REQUEST);
		} else {
			charService.update(character);
			return new ResponseEntity<>(new String("Se ha actualizado con éxito"), HttpStatus.OK);

		}

	}
	//metodo para borrar un personaje
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if(charService.findById(id)==null){
			return new ResponseEntity<>(new String("El id es incorrecto."),HttpStatus.BAD_REQUEST);
		}
		else {
			charService.delete(id);
		return new ResponseEntity<>(new String("Se ha borrado con éxito."),HttpStatus.OK);
		}
	}
	
	
	
	//metodo para filtros
	private List<CharacterDto> mapListToDto(List<Character> list){
		List<CharacterDto> auxList = new ArrayList<>();
		for(Character c : list) {
			CharacterDto cc = new CharacterDto();
			cc =mapToDto(c);
			auxList.add(cc);
		}
		return auxList;
	}
	
	// metodo para mapear dto
	private CharacterDto mapToDto(Character c) {
		CharacterDto charDto =mapper.map(c,CharacterDto.class);
		
		return charDto;
	}
	//metodo para mapear entity
	private Character mapToEntity(CharacterDto charDto) {
		Character character =mapper.map(charDto,Character.class);
		return character;
	}
	

}
