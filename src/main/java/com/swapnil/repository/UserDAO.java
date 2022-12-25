package com.swapnil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>{

	public Optional<User> findByMobileNumber(String mobileNumber);
}
