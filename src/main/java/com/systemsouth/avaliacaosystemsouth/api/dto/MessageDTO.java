package com.systemsouth.avaliacaosystemsouth.api.dto;

public class MessageDTO implements ResponseDTO {

	private String message;

	public MessageDTO(String message) {
		super();
		this.message = message;
	}

	public MessageDTO() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
