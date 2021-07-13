package com.api.challenge.dto;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor @NoArgsConstructor
public class CharacterDtoList implements Serializable {
	
	private static final long serialVersionUID = -5374565387490823046L;
	private Long id;
	private String name;
	private String picture;

}
