package com.swapnil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.swapnil.model.MyUser;

import com.swapnil.model.UserSecurity;
import com.swapnil.repository.MyUserDAO;


@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private MyUserDAO mDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

			MyUser my=mDao.findByUserName(username);
		 UserDetails u=new UserSecurity(my);
		
		if(my==null) {
			throw new UsernameNotFoundException("User Not found");
		}
		return u;
	}

}
