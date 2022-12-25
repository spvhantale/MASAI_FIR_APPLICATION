package com.swapnil.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.swapnil.dto.LoginDTO;
import com.swapnil.exception.CurrentUserException;
import com.swapnil.exception.FIRException;
import com.swapnil.exception.UserException;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.FIR;
import com.swapnil.model.MyUser;
import com.swapnil.model.User;
import com.swapnil.repository.FirDAO;
import com.swapnil.repository.MyUserDAO;
import com.swapnil.repository.SessionDAO;
import com.swapnil.repository.UserDAO;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO uDao;
	@Autowired
	private MyUserDAO mDao;
	@Autowired
	private FirDAO fDao;
	@Autowired
	private PasswordEncoder pEnd;
	@Autowired
	private SessionDAO sDao;
	
	@Override
	public User registerUser(User user) throws UserException {
		
		Optional<User> u=uDao.findByMobileNumber(user.getMobileNumber());
		
		if(u.isPresent()) {
			throw new UserException("Change mobile");
		}else {
			String s=user.getPassword();
			user.setPassword(pEnd.encode(user.getPassword()));
			MyUser myuser=new MyUser(user.getMobileNumber(), user.getFirstName(), user.getPassword(), "user");
			mDao.save(myuser);
			User us=uDao.save(user);
			us.setPassword(s);
			return us;
		}

	}

	@Override
	public CurrentUserSession login(LoginDTO login) throws CurrentUserException {
		
Optional<CurrentUserSession> c=sDao.findByUserName(login.getUserName());
		
		if(c.isPresent()) {
			throw new CurrentUserException("Already login");
		}else {
			Optional<User> u=uDao.findByMobileNumber(login.getUserName());
			if(u.isPresent()) {
			String key=RandomString.make(6);
			CurrentUserSession cu=new CurrentUserSession(u.get().getUserId(), login.getUserName(), key, LocalDateTime.now());
			
			CurrentUserSession curr=sDao.save(cu);
			return curr;
			}else {
				throw new CurrentUserException("User not found");
			}
		}
	}

	@Override
	public List<FIR> getAllFIR(String key) throws CurrentUserException,UserException,FIRException {

		Optional<CurrentUserSession> s=sDao.findByUuid(key);
		
		if(s.isPresent()) {
			CurrentUserSession c=s.get();
			Optional<User> u=uDao.findByMobileNumber(c.getUserName());
			if(u.isPresent()) {
				User use=u.get();
				List<FIR> flist=use.getFirs();
				if(flist.isEmpty()) {
					throw new FIRException("Fir not register");
				}else {
					return flist;
				}
			}else {
				throw new UserException("User not found");
			}
			
		}else {
			throw new CurrentUserException("user not found");
		}
	}

	@Override
	public String deleteFIR(String key,Integer firId) throws FIRException, CurrentUserException {
Optional<CurrentUserSession> s=sDao.findByUuid(key);
		
		if(s.isPresent()) {
			CurrentUserSession c=s.get();
			Optional<User> u=uDao.findByMobileNumber(c.getUserName());
			if(u.isPresent()) {
				Optional<FIR> fi=fDao.findById(firId);
				if(fi.isPresent()) {
					User use=u.get();
					List<FIR> flist=use.getFirs();
					
					FIR firr=fi.get();
					LocalDate date=firr.getTimestamp().toLocalDate();
					
					Period p=Period.between(LocalDate.now(), date);
					int day=p.getDays();
					if(day>=1) {
						throw new FIRException("Can not delete the fir 24 hours completed");
					}else {
						flist.remove(firr);
						
						FIR fir=fi.get();
						fDao.delete(fir);
						return "Deleted";
					}
					
					
				}else {
					throw new FIRException("Fir not present");
				}
				
				
			}else {
				throw new CurrentUserException("user not found");
			}
			
			
		}else {
			throw new CurrentUserException("user not found");
		}
	}

}
