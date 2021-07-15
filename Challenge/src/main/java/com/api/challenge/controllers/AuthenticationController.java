package com.api.challenge.controllers;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.challenge.dto.UserDto;
import com.api.challenge.models.AuthenticationRequest;
import com.api.challenge.models.AuthenticationResponse;
import com.api.challenge.models.User;
import com.api.challenge.services.EmailService;
import com.api.challenge.services.MyUserDetailsService;
import com.api.challenge.services.UserService;
import com.api.challenge.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtToken;
	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;
	
	private ModelMapper mapper = new ModelMapper();
	


	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authReq) throws Exception{
		try {
		authManager.authenticate(
				new UsernamePasswordAuthenticationToken(authReq.getUsername(),authReq.getPassword())
				);
		}catch (BadCredentialsException e) {
			throw new Exception("El usuario y/o contraseña es incorrecto", e);
		}
	
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authReq.getUsername());
		
		final String jwt = jwtToken.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws IOException{
		if(userDto.getUserName() == null || userDto.getEmail() == null || userDto.getPassword() == null) {
			return new ResponseEntity<>(new String("Debe contener un usuario, contraseña y email."), HttpStatus.BAD_REQUEST);
		} else {
			
			
			 User user = mapToEntity(userDto);
			 user = userService.createUser(user);
			 String email = user.getEmail();
			 emailService.sendEmail(email);
			 
				UserDto userDtoAux = mapToDto(user);
			return new ResponseEntity<>(userDtoAux,HttpStatus.OK);
		}
		
	}
	
	// metodo para mapear dto
		private UserDto mapToDto(User u) {
			UserDto userDto =mapper.map(u,UserDto.class);
			
			return userDto;
		}
		//metodo para mapear entity
		private User mapToEntity(UserDto userDto) {
			User user =mapper.map(userDto,User.class);
			return user;
		}
		
	
	
	
}

