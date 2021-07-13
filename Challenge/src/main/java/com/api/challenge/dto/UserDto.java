package com.api.challenge.dto;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor @NoArgsConstructor
public class UserDto implements Serializable{
	
	private static final long serialVersionUID = -3797146935506521357L;

	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String photo;
	private String password;
	

}
