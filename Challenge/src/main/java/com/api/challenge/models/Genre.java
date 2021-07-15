package com.api.challenge.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "genre")
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable =false)
	private Long id;
	
	@NotEmpty
	@Column
	private String name;
	
	@NotEmpty
	@Column
	private String picture;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy="genre")
	@JsonIgnore
	private List<Movie> movies = new ArrayList<>();

}
