package com.api.challenge.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable =false)
	private Long id;
	
	@NotEmpty
	@Column
	private String title;
	
	@NotEmpty
	@Column
	private String picture;
	
	
	@Column
	private LocalDate creation;
	
	@Column
	@Min(value=1)
	@Max(value=5)
	private Integer qualification;
	
	@ManyToMany(mappedBy= "movies",cascade = CascadeType.ALL)
	private List<Character> characters = new ArrayList<>();
	
	@ManyToOne()
	@JoinColumn(name="genre_id")
	private Genre genre;
	
	public String formatDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String formatDate = sdf.format(creation);
		return formatDate;
	}
	
	public Date formatString(String string) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
			Date fecha = sdf.parse(string);
		
		return fecha;
	}
	
}
