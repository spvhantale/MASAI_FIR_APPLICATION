package com.swapnil.service;

import com.swapnil.dto.FIRDTO;
import com.swapnil.exception.CurrentUserException;
import com.swapnil.exception.FIRException;
import com.swapnil.exception.UserException;
import com.swapnil.model.FIR;

public interface FIRService {

	public FIR registerFIR(FIRDTO firDto,String key)throws FIRException,UserException,CurrentUserException;
}
