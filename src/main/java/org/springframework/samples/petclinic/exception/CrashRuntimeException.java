package org.springframework.samples.petclinic.exception;

public class CrashRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CrashRuntimeException(String errorMessage) {
        super(errorMessage);
    }
}
