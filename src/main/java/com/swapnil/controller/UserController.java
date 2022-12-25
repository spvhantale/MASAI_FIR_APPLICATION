package com.swapnil.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.dto.LoginDTO;
import com.swapnil.exception.CurrentUserException;
import com.swapnil.exception.FIRException;
import com.swapnil.exception.UserException;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.FIR;
import com.swapnil.model.User;
import com.swapnil.service.UserService;

@RestController
@RequestMapping("/masaifir/user")
public class UserController {

	@Autowired
	private UserService uService;
	
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user) throws UserException{
		
		User s=uService.registerUser(user);
		
		return new ResponseEntity<User>(s, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<CurrentUserSession> loginUser(@Valid @RequestBody LoginDTO loginDTO) throws CurrentUserException{
		
		CurrentUserSession s=uService.login(loginDTO);
		
		return new ResponseEntity<CurrentUserSession>(s, HttpStatus.OK);
	}
	
	@GetMapping("/fir")
	public ResponseEntity<List<FIR>> getAllFIR(@RequestParam String key) throws UserException, CurrentUserException, FIRException{
		
		List<FIR> s=uService.getAllFIR(key);
		
		return new ResponseEntity<List<FIR>>(s, HttpStatus.CREATED);
	}
	@DeleteMapping("/fir/{firId}")
	public ResponseEntity<String> deletefir(@PathVariable("firId") Integer firId,@RequestParam String key) throws UserException, CurrentUserException, FIRException{
		
		String s=uService.deleteFIR(key, firId);
		
		return new ResponseEntity<String>(s, HttpStatus.CREATED);
	}
}
