package com.stackroute.favouriteservice.exception;

public class FoodAlreadyExistsException extends Exception {
	
	private String message;
	
	public FoodAlreadyExistsException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "foodAlreadyExistsException [message=" + message + "]";
	}	
	
}
