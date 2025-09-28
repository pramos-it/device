package com.pramos.devices.exceptions;

public class AirConditionerException extends BusinessException {

	private static final long serialVersionUID = -8132553673989083497L;

	public AirConditionerException() {
		super();
	}
	
	public AirConditionerException(String message) {
		super(message);
	}

	public AirConditionerException(Throwable cause) {
		super(cause);
	}
}
