package com.swapnil.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Myconfiguration {



	@Bean
	public SecurityFilterChain securityConfig(HttpSecurity http)throws Exception{
		
		http.authorizeHttpRequests((auth)->auth.antMatchers("/masaifir/user/login","/masaifir/user/fir","/masaifir/user/fir/{firId}").hasAuthority("user")
				.antMatchers("/masaifir/user/register").permitAll()
				).csrf().disable().httpBasic();
		
	return	http.build();
	}
	
	@Bean
	public PasswordEncoder encodePassword() {
		//return NoOpPasswordEncoder.getInstance(); //Not recommended
		return new BCryptPasswordEncoder();
	}
}
