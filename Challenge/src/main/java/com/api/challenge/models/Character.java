package com.api.challenge.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "personage")
public class Character {
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
	
	@Column
	private String age;
	
	@Column
	private Double weight;
	
	@Column
	private String story;
	
	@ManyToMany(cascade =CascadeType.ALL)
	@JoinTable(name="personage_movie",
	joinColumns =  @JoinColumn(name ="personage_fk"),
	inverseJoinColumns =  @JoinColumn(name ="movie_fk") )
	
	private List<Movie> movies = new ArrayList<>();
	
	
	
}