package com.stackroute.userservice.service;

import com.stackroute.userservice.entity.User;
import com.stackroute.userservice.exception.UserAlreadyExsitsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {	
	
	private UserRepository repo;
	
	@Autowired
	public UserServiceImpl(UserRepository repo) {
		super();
		this.repo = repo;
	}


	@Override
	public boolean saveUser(User user) throws UserAlreadyExsitsException, UserNotFoundException {
		Optional<User> u = repo.findById(user.getUserId());
		if(u.isPresent()) {
			throw new UserAlreadyExsitsException("User with Id already exsits");
		}
		repo.save(user);
		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user = repo.findByUserIdAndPassword(userId, password);
		if(user == null) {
			throw new UserNotFoundException("UserId and password do not match");
		}
		return user;
	}

}
