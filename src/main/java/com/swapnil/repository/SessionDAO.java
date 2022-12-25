package com.swapnil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.CurrentUserSession;
@Repository
public interface SessionDAO extends JpaRepository<CurrentUserSession, Integer>{

	public Optional<CurrentUserSession> findByUuid(String uuid);
	
	public Optional<CurrentUserSession> findByUserName(String userName);
}
