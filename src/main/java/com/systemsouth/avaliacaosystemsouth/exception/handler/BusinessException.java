package com.systemsouth.avaliacaosystemsouth.exception.handler;

import java.io.IOException;

public class BusinessException extends IOException {
 
	private static final long serialVersionUID = 1L;
	
	private ApiError apiError;

	public BusinessException(ApiError apiError) {
		this.setApiError(apiError);
	}

	public ApiError getApiError() {
		return apiError;
	}

	public void setApiError(ApiError apiError) {
		this.apiError = apiError;
	}

}
