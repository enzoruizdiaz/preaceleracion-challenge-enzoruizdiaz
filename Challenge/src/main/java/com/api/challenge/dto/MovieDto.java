package com.api.challenge.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.api.challenge.models.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data@AllArgsConstructor @NoArgsConstructor
public class MovieDto implements Serializable {
	
	private static final long serialVersionUID = 8307769142105090646L;
	
	private Long id;
	private String title;
	private String picture;
	private LocalDate creation;
	private Integer qualification;
	private	Genre genre;
	@JsonIgnoreProperties (value ={"movies","characters"})
	private List<CharacterDto> characters = new ArrayList<>();
}
