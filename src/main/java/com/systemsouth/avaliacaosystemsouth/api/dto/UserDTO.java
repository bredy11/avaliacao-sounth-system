package com.systemsouth.avaliacaosystemsouth.api.dto;

import com.systemsouth.avaliacaosystemsouth.domain.User;

public class UserDTO implements DTO {

	private Long id;
	private String document;

	public UserDTO(User user) {
		this.document = user.getDocument();
	}

	public UserDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}
}
