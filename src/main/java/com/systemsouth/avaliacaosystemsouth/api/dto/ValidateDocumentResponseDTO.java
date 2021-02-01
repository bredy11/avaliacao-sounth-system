package com.systemsouth.avaliacaosystemsouth.api.dto;

import com.systemsouth.avaliacaosystemsouth.api.enuns.StatusValideteDocument;

public class ValidateDocumentResponseDTO implements ResponseDTO {

	private StatusValideteDocument status;

	public StatusValideteDocument getStatus() {
		return status;
	}

	public void setStatus(StatusValideteDocument status) {
		this.status = status;
	}
	
}
