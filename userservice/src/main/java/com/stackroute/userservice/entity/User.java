package com.stackroute.userservice.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name = "user_id",length = 64)
	private String userId;
	
	@Column(name = "firstname",length = 64)
	private String firstname;
	
	@Column(name = "lastname",length = 64)
	private String lastname;
	
	@Column(name = "password",length = 64)
	private String password;

	
	public User() {}

	public User(String userId, String firstname, String lastname, String password) {
		super();
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
