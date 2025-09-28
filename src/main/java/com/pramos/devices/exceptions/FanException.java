package com.pramos.devices.exceptions;

public class FanException extends BusinessException {

	private static final long serialVersionUID = -8132553673989083497L;

	public FanException() {
		super();
	}
	
	public FanException(String message) {
		super(message);
	}

	public FanException(Throwable cause) {
		super(cause);
	}
}
