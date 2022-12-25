package com.swapnil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.MyUser;

@Repository
public interface MyUserDAO extends JpaRepository<MyUser, Integer> {

	public MyUser findByUserName(String userName);
}
