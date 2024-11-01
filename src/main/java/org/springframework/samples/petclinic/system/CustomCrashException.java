package org.springframework.samples.petclinic.system;

public class CustomCrashException extends RuntimeException {

	public CustomCrashException(String message) {
		super(message);
	}

}
