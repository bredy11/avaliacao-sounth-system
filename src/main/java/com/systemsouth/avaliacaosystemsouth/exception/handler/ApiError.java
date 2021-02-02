package com.systemsouth.avaliacaosystemsouth.exception.handler;

import org.springframework.http.HttpStatus;

import com.systemsouth.avaliacaosystemsouth.api.dto.ResponseDTO;
/**
 * Todos os erros da aplicação deve ser retornado esse objeto
 * @author rafa1
 *
 */
public class ApiError implements ResponseDTO  {

    private Integer code;
    private String title;
    private String detail;

    public ApiError(HttpStatus status, Integer code) {
        this.code = code;
        this.title = status.getReasonPhrase();
        
    }

    
 
    public ApiError(Integer code, String title, String detail) {
		this.code = code;
		this.title = title;
		this.detail = detail;
	}



	public void setCode(Integer code) {
		this.code = code;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public void setDetail(String detail) {
		this.detail = detail;
	}



	public Integer getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

}
