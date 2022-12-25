package com.swapnil.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.dto.FIRDTO;
import com.swapnil.exception.CurrentUserException;
import com.swapnil.exception.FIRException;
import com.swapnil.exception.UserException;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.FIR;
import com.swapnil.model.User;
import com.swapnil.repository.FirDAO;
import com.swapnil.repository.SessionDAO;
import com.swapnil.repository.UserDAO;

@Service
public class FIRServiceImpl implements FIRService{

	@Autowired
	private FirDAO fDao;
	@Autowired
	private UserDAO uDao;
	@Autowired
	private SessionDAO sDao;
	
	@Override
	public FIR registerFIR(FIRDTO firdto, String key) throws FIRException, UserException, CurrentUserException {
		
		Optional<CurrentUserSession> c=sDao.findByUuid(key);
		
		if(c.isPresent()) {
			
			CurrentUserSession cu=c.get();
			Optional<User> us=uDao.findByMobileNumber(cu.getUserName());
			if(us.isPresent()) {
				User user=us.get();
				List<String> f=firdto.getMobileNumber();
				FIR fir=new FIR(firdto.getCrimeDetail(), LocalDateTime.now(), firdto.getPoliceStation(), user);
				for(String s:f) {
					Optional<User> use=uDao.findByMobileNumber(s);
					System.out.println(use);
					if(use.isPresent()) {
						User ss=use.get();
						fir.getUser().add(ss);
						System.out.println(f);
					}
				}
				FIR fri=fDao.save(fir);
			return fri;
				
			}else {
				throw new UserException("User not found");
			}
			
		}else {
			throw new CurrentUserException("User not found OR Login first");
		}
		
	}

}
