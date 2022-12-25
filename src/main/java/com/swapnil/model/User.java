package com.swapnil.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@Pattern(regexp = "^[a-zA-Z]*$", message = "First Name Special Characters are not Allowed.")
	private String firstName;
	
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Last Name Special Characters are not Allowed.")
	private String lastName;
	
	@Column(unique = true)
	@Size(min = 10,max = 10 ,message = "mobile number should be 10 digit")
	private String mobileNumber;
	
	@Min(value = 8 ,message = "age should be greater than 8")
	private Integer age;
	
	@Enumerated(EnumType.STRING)
	private EGender gender;
	
	private String address;

	
	@Pattern(regexp = "((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])).{6,100}$", message = "password not allowed")
	private String password;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "officer")
	private List<FIR> firs=new ArrayList<>();
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", mobileNumber="
				+ mobileNumber + ", age=" + age + ", gender=" + gender + "]";
	}
	
	
}
