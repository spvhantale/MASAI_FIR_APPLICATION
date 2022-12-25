package com.swapnil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.FIR;

@Repository
public interface FirDAO extends JpaRepository<FIR, Integer>{

	
}
