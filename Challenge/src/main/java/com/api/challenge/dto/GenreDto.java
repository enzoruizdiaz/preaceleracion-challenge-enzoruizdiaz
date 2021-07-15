package com.api.challenge.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class GenreDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String picture;
	@JsonIgnoreProperties(value="genre")
	private List<MovieDto> movies;

	
}
