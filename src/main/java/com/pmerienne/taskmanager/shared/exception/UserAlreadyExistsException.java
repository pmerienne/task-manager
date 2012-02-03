package com.pmerienne.taskmanager.shared.exception;

public class UserAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1394957743781136205L;

	public UserAlreadyExistsException() {
		super();
	}

	public UserAlreadyExistsException(String message) {
		super(message);
	}

}
