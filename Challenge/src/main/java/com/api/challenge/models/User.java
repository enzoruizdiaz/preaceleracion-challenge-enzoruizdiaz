package com.api.challenge.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="user")
public class User implements Serializable {

	private static final long serialVersionUID = 8031703671286259899L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	@NotEmpty
	private String userName;
	
	@Column(nullable = false)
	@NotEmpty
	private String firstName;
	
	@Column(nullable = false)
	@NotEmpty
	private String lastName;
	
	@Column(nullable = false)
	@NotEmpty
	private String email;
	
	@Column(nullable = false)
	@NotEmpty
    private String password;
	
	private String photo;
	
	private Boolean active;
	
	@CreationTimestamp
    @Column(name = "create_at")
    private Timestamp create_at_register;
	
	
	
	
	
	
	
}
