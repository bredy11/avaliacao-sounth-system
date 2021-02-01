package com.systemsouth.avaliacaosystemsouth.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.systemsouth.avaliacaosystemsouth.api.dto.MessageDTO;
import com.systemsouth.avaliacaosystemsouth.api.dto.ResponseDTO;
import com.systemsouth.avaliacaosystemsouth.api.dto.TopicDTO;
import com.systemsouth.avaliacaosystemsouth.service.TopicService;

@RestController
@RequestMapping(value = "/v1/topic")
public class TopicController {

	@Autowired
	private TopicService topicService;

	@GetMapping
	public ResponseEntity<List<TopicDTO>> getTopic() {
		return ResponseEntity.ok(topicService.getTopic());
	}

	@PostMapping
	public ResponseEntity<TopicDTO> creatTopic(@RequestBody TopicDTO topicDTO) {
		return new ResponseEntity<>(topicService.creatTopic(topicDTO),HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<TopicDTO> updateTopic(@RequestBody TopicDTO topicDTO) {
		return ResponseEntity.ok(topicService.updateTopic(topicDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MessageDTO> deleteTopic(@PathVariable Long id) {
		topicService.deleteTopic(id);
		return ResponseEntity.ok(new MessageDTO("Pauta deletada com sucesso"));
	}

	@PutMapping("/{id}")
	public ResponseEntity<MessageDTO> startTopic(@PathVariable Long id) {
		topicService.startTopic(id);
		return ResponseEntity.ok(new MessageDTO("Votação iniciada"));
	}

}
