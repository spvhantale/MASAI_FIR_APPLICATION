package com.swapnil.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.dto.FIRDTO;
import com.swapnil.exception.CurrentUserException;
import com.swapnil.exception.FIRException;
import com.swapnil.exception.UserException;
import com.swapnil.model.FIR;
import com.swapnil.service.FIRService;

@RestController
@RequestMapping("/masaifir/user")
public class FIRController {

	@Autowired
	private FIRService fService;
	
	
	@PostMapping("/fir")
	public ResponseEntity<FIR> registerFIR(@Valid @RequestBody FIRDTO firdto,@RequestParam String key) throws FIRException, UserException, CurrentUserException{
		FIR f=fService.registerFIR(firdto, key);
		
		return new ResponseEntity<FIR>(f, HttpStatus.CREATED);
	}
}
