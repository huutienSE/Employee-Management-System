package com.ems.exception;

public class DuplicateIdException extends Exception{
	public DuplicateIdException() {
		super();
	}
	
	public DuplicateIdException(String message) {
		super(message);
	}
}
