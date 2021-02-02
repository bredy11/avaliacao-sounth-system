package com.systemsouth.avaliacaosystemsouth.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.systemsouth.avaliacaosystemsouth.api.dto.ValidateDocumentResponseDTO;

/**
 * Integração com o serviço de validação do documento
 * 
 * @author rafa1
 *
 */
@Component
public class IntegrationValidateDocumentImpl implements IntegrationValidateDocument {

	@Value("${south.system.url.validate.document}")
	private String url;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Validar cpf
	 */
	@Override
	public ValidateDocumentResponseDTO validateDocument(String document) {
		String uri = url + document;
		return restTemplate.exchange(uri, HttpMethod.GET, getAuthHeader(), ValidateDocumentResponseDTO.class).getBody();
	}

	private HttpEntity<?> getAuthHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity<Object>(headers);
		return entity;
	}
}
