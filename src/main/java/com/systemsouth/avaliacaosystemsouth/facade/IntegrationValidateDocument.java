package com.systemsouth.avaliacaosystemsouth.facade;

import com.systemsouth.avaliacaosystemsouth.api.dto.ValidateDocumentResponseDTO;

public interface IntegrationValidateDocument {

	ValidateDocumentResponseDTO validateDocument(String document);
	
}
