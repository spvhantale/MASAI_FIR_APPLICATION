package com.swapnil.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.swapnil.dto.LoginDTO;
import com.swapnil.exception.CurrentUserException;
import com.swapnil.exception.FIRException;
import com.swapnil.exception.UserException;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.FIR;
import com.swapnil.model.User;

public interface UserService {

	public User registerUser(User user)throws UserException;
	
	public CurrentUserSession login(LoginDTO login) throws CurrentUserException;
	public List<FIR> getAllFIR(String key) throws CurrentUserException,UserException,FIRException;
	
	public String deleteFIR(String key,Integer firId)throws FIRException,CurrentUserException;
}
