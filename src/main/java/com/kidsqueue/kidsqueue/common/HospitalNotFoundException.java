package com.kidsqueue.kidsqueue.common;

public class HospitalNotFoundException extends RuntimeException {

	public HospitalNotFoundException(String message) {
		super(message);
	}

	public HospitalNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}