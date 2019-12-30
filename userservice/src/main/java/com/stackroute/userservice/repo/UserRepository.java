package com.stackroute.userservice.repo;

import com.stackroute.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
	
	User findByUserIdAndPassword(String userId, String password);

}
