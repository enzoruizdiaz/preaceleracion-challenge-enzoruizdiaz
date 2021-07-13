package com.api.challenge.dto;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor @NoArgsConstructor
public class MovieDtoList implements Serializable {

	private static final long serialVersionUID = -3224165744713375132L;
	private Long id;
	private String title;
	private String picture;
	private LocalDate creation;
}
