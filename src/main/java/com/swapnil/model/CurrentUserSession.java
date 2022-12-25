package com.swapnil.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class CurrentUserSession {

	@Id
	private Integer userId;
	private String userName;
	private String uuid;
	private LocalDateTime time;
	
}
