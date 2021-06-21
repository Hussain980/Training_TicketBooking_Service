package com.example.demo.exception;

public class TrainNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TrainNotFoundException(String message) {
		super(message);
	}

}
