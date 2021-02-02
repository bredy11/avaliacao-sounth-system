package com.systemsouth.avaliacaosystemsouth.api.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemsouth.avaliacaosystemsouth.AvaliacaoSouthSystemApplication;
import com.systemsouth.avaliacaosystemsouth.api.dto.TopicDTO;
import com.systemsouth.avaliacaosystemsouth.domain.Topic;
import com.systemsouth.avaliacaosystemsouth.repository.TopicRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AvaliacaoSouthSystemApplication.class)
@AutoConfigureMockMvc
public class TopicControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private TopicRepository topicRepository;

	@Test
	public void getTopic() throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/v1/topic");

		String url = builder.build().toString();
		when(topicRepository.findAll()).thenReturn(Arrays.asList(getEntityTopic()));
		mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void creatTopic() throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/v1/topic");

		String url = builder.build().toString();
		TopicDTO topicDTO = new TopicDTO();
		topicDTO.setText("teste");
		String json = new ObjectMapper().writeValueAsString(topicDTO);

		when(topicRepository.save(Mockito.any(Topic.class))).thenReturn(getEntityTopic());
		mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated());
	}

	@Test
	public void updateTopic() throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/v1/topic");

		String url = builder.build().toString();
		TopicDTO topicDTO = new TopicDTO();
		topicDTO.setId(1L);
		topicDTO.setText("teste");
		String json = new ObjectMapper().writeValueAsString(topicDTO);

		when(topicRepository.save(Mockito.any(Topic.class))).thenReturn(getEntityTopic());
		mvc.perform(put(url).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
	}

	@Test
	public void deleteTopic() throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/v1/topic/1");

		String url = builder.build().toString();

		doNothing().when(topicRepository).delete(Mockito.any(Topic.class));
		mvc.perform(delete(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void startTopic() throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/v1/topic/1");

		String url = builder.build().toString();

		when(topicRepository.findById(Mockito.any())).thenReturn(Optional.of(getEntityTopic()));

		when(topicRepository.save(Mockito.any(Topic.class))).thenReturn(getEntityTopic());
		mvc.perform(put(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	private Topic getEntityTopic() {
		Topic topic = new Topic();
		topic.setId(1L);
		topic.setText("teste");
		return topic;
	}

}
