package com.swapnil.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	@Column(unique = true)
	private String userName;
	private String name;
	private String password;
	private String role;
	
	public MyUser(String userName, String name, String password, String role) {
		super();
		this.userName = userName;
		this.name = name;
		this.password = password;
		this.role = role;
	}
	
	
	

	
}

