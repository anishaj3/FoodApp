package com.stackroute.userservice.service;

import com.stackroute.userservice.entity.User;
import com.stackroute.userservice.exception.UserAlreadyExsitsException;
import com.stackroute.userservice.exception.UserNotFoundException;

public interface UserService {
	
	boolean saveUser (User user) throws UserAlreadyExsitsException, UserNotFoundException;
	User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;

}
