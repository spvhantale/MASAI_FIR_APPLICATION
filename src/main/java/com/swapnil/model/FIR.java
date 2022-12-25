package com.swapnil.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FIR {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer firId;
	@Size(min = 3,max = 25,message = "enter proper name")
	private String crimeDetail;
	private LocalDateTime timestamp;
	@Size(min = 3,max = 25,message = "enter proper policestation")
	private String policeStation;
	@ManyToOne
	private User officer;
	@OneToMany
	private List<User> user=new ArrayList<>();
	public FIR(String crimeDetail, LocalDateTime timestamp, String policeStaion, User officer) {
		super();
		this.crimeDetail = crimeDetail;
		this.timestamp = timestamp;
		this.policeStation = policeStaion;
		this.officer = officer;
	}
	

	
	
}
