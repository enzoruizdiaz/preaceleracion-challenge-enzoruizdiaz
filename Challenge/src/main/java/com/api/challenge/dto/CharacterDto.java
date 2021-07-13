package com.api.challenge.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor @NoArgsConstructor
public class CharacterDto implements Serializable{
	
	private static final long serialVersionUID = 6847146105649840669L;
	private Long id;
	private String name;
	private String picture;
	private String age;
	private Double weight;
	private String story;
	
	@JsonIgnoreProperties (value ="characters")
	private List<MovieDto> movies = new ArrayList<>();

}
