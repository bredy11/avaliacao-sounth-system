package com.systemsouth.avaliacaosystemsouth.exception.handler;

 
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

     

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                || response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        Integer codeMessage = HttpStatusMessagesEnum.getHttpStatusMessageFromHttpStatus(response.getStatusCode()).codeMessage();
        throw new BusinessException(new ApiError(response.getStatusCode(), codeMessage));
    }

}
