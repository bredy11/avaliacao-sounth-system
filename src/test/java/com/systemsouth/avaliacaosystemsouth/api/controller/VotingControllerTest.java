package com.systemsouth.avaliacaosystemsouth.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
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
import com.systemsouth.avaliacaosystemsouth.api.dto.ValidateDocumentResponseDTO;
import com.systemsouth.avaliacaosystemsouth.api.dto.VotingDTO;
import com.systemsouth.avaliacaosystemsouth.api.enuns.StatusValideteDocument;
import com.systemsouth.avaliacaosystemsouth.domain.Topic;
import com.systemsouth.avaliacaosystemsouth.domain.User;
import com.systemsouth.avaliacaosystemsouth.domain.Voting;
import com.systemsouth.avaliacaosystemsouth.facade.IntegrationValidateDocumentImpl;
import com.systemsouth.avaliacaosystemsouth.repository.TopicRepository;
import com.systemsouth.avaliacaosystemsouth.repository.UserRepository;
import com.systemsouth.avaliacaosystemsouth.repository.VotingRepository;
import com.systemsouth.avaliacaosystemsouth.service.TopicService;
import com.systemsouth.avaliacaosystemsouth.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AvaliacaoSouthSystemApplication.class)
@AutoConfigureMockMvc
public class VotingControllerTest {

	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private TopicService topicService;
		 
	@MockBean
	private UserService userService;
	
	@MockBean
	private VotingRepository votingRepository;

	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private IntegrationValidateDocumentImpl integrationValidateDocument;
	
	@Test
	public void polling() throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/v1/voting");

		String url = builder.build().toString();
		VotingDTO votingDTO = new VotingDTO();
		votingDTO.setVote("Sim");
		votingDTO.setIdTopic(1L);
		votingDTO.setDocument("07864025048");
		
		String json = new ObjectMapper().writeValueAsString(votingDTO);

		when(topicService.findById(Mockito.any())).thenReturn(getEntityTopic());
		when(userService.findbyDocument(Mockito.any())).thenReturn(getUser());
		when(votingRepository.save(Mockito.any(Voting.class))).thenReturn(new Voting());

		
		mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
		
	}
	
	@Test
	public void testCreateUser() throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/v1/voting");

		String url = builder.build().toString();
		VotingDTO votingDTO = new VotingDTO();
		votingDTO.setVote("Sim");
		votingDTO.setIdTopic(1L);
		votingDTO.setDocument("07864025048");
		
		String json = new ObjectMapper().writeValueAsString(votingDTO);

		when(topicService.findById(Mockito.any())).thenReturn(getEntityTopic());
		when(userService.findbyDocument(Mockito.any())).thenReturn(null);
		
		when(userRepository.save(Mockito.any(User.class))).thenReturn(getUser());
		when(integrationValidateDocument.validateDocument(Mockito.anyString())).thenReturn(getValideteDocument());
		when(votingRepository.save(Mockito.any(Voting.class))).thenReturn(new Voting());

		
		mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
		
	}
	
	
	private ValidateDocumentResponseDTO getValideteDocument() {
		ValidateDocumentResponseDTO validateDocumentResponseDTO =  new ValidateDocumentResponseDTO();
		validateDocumentResponseDTO.setStatus(StatusValideteDocument.ABLE_TO_VOTE);
		return validateDocumentResponseDTO;
	}

	private User getUser() {
		User user = new User();
		user.setDocument("07864025048");
		user.setId(1L);
		return user;
	}
	private Topic getEntityTopic() {
		Topic topic = new Topic();
		topic.setId(1L);
		topic.setText("teste");
		topic.setVotingStart(LocalDateTime.now());
		return topic;
	}
}
