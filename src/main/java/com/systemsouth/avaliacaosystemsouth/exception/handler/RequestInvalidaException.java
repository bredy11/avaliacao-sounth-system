package com.systemsouth.avaliacaosystemsouth.exception.handler;

public class RequestInvalidaException  extends RuntimeException{

	private String message;

	
	
	public RequestInvalidaException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
