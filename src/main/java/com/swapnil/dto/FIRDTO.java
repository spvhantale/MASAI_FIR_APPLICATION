package com.swapnil.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FIRDTO {

	private String crimeDetail;
	private String policeStation;
	private List<String> mobileNumber; 
}
