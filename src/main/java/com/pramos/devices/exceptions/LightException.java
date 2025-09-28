package com.pramos.devices.exceptions;

public class LightException extends BusinessException {

	private static final long serialVersionUID = -8132553673989083497L;

	public LightException() {
		super();
	}
	
	public LightException(String message) {
		super(message);
	}

	public LightException(Throwable cause) {
		super(cause);
	}
}
