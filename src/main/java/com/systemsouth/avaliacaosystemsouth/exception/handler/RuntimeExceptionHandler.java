package com.systemsouth.avaliacaosystemsouth.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.systemsouth.avaliacaosystemsouth.api.dto.ResponseDTO;

/**
 * Capitura o erro lançado na controller e personaliza a respota
 * 
 * @author rafa1
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RuntimeExceptionHandler {

	@ExceptionHandler(value = BusinessException.class)
	public ResponseEntity<ResponseDTO> handle(BusinessException businessException) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_PROBLEM_JSON)
				.body(businessException.getApiError());
	}

	@ExceptionHandler(value = InvalidDocumentException.class)
	public ResponseEntity<ResponseDTO> handle(InvalidDocumentException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_PROBLEM_JSON)
				.body(new ApiError(1, exception.getMessage(), null));
	}

	@ExceptionHandler(value = RequestInvalidaException.class)
	public ResponseEntity<ResponseDTO> handle(RequestInvalidaException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_PROBLEM_JSON)
				.body(new ApiError(1, exception.getMessage(), null));
	}

	@ExceptionHandler(value = VotingNotStartedException.class)
	public ResponseEntity<ResponseDTO> handle(VotingNotStartedException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_PROBLEM_JSON)
				.body(new ApiError(1, exception.getMessage(), null));
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ResponseDTO> handle(Exception exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_PROBLEM_JSON)
				.body(new ApiError(1, "Erro interno", null));
	}
}