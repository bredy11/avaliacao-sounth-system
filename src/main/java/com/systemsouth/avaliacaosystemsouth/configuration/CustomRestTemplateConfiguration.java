package com.systemsouth.avaliacaosystemsouth.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import com.systemsouth.avaliacaosystemsouth.exception.handler.RestTemplateResponseErrorHandler;

import java.time.Duration;

@Configuration
public class CustomRestTemplateConfiguration {

	@Value("${south.system.connection.timeout}")
	private Long connectionTimeout;

	@Value("${south.system.read.timeout}")
	private Long readTimeout;

	/**
	 * Configuração de resttemplate, e definição de dto unico de erro
	 * 
	 * @param restTemplate
	 * @return
	 */
	@Bean
	@Primary
	public RestTemplate getRestTemplate(RestTemplateBuilder restTemplate) {
		return restTemplate.setConnectTimeout(Duration.ofMillis(connectionTimeout))
				.setReadTimeout(Duration.ofMillis(readTimeout)).errorHandler(new RestTemplateResponseErrorHandler())
				.build();
	}

}
