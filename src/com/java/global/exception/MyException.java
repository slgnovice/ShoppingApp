package com.java.global.exception;

public class MyException extends RuntimeException{
	
	
	private String message;

	public MyException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}	

}
