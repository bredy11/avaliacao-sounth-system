package com.systemsouth.avaliacaosystemsouth.exception.handler;

public class InvalidDocumentException  extends RuntimeException{

	private String message;

	
	
	public InvalidDocumentException(String message) {
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
