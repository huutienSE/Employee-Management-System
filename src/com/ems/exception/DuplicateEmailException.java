package com.ems.exception;

public class DuplicateEmailException extends Exception {
	public DuplicateEmailException() {
		super();
	}
	
	public DuplicateEmailException(String message) {
		super(message);
	}
}
