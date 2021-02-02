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
/**
 * A controller foi adicionado um contexto versionado para permitir melhorias no sistema sem precisar prejudicar serviços que esteja ja integrado
 * @author rafa1
 *
 */
@RestController
@RequestMapping(value = "/v1/topic")
public class TopicController {

	@Autowired
	private TopicService topicService;

	/**
	 * devove todas as pautas
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<TopicDTO>> getTopic() {
		return ResponseEntity.ok(topicService.getTopic());
	}

	/**
	 * criar pauta
	 * 
	 * @return
	 */
	@PostMapping
	public ResponseEntity<TopicDTO> creatTopic(@RequestBody TopicDTO topicDTO) {
		return new ResponseEntity<>(topicService.creatTopic(topicDTO), HttpStatus.CREATED);
	}

	/**
	 * Edita uma pauta, o id deve ser passado na request
	 * 
	 * @return
	 */
	@PutMapping
	public ResponseEntity<TopicDTO> updateTopic(@RequestBody TopicDTO topicDTO) {
		return ResponseEntity.ok(topicService.updateTopic(topicDTO));
	}

	/**
	 * Deleta uma pauta
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageDTO> deleteTopic(@PathVariable Long id) {
		topicService.deleteTopic(id);
		return ResponseEntity.ok(new MessageDTO("Pauta deletada com sucesso"));
	}

	/**
	 *Começa  a votação 
	 * 
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<MessageDTO> startTopic(@PathVariable Long id) {
		topicService.startTopic(id);
		return ResponseEntity.ok(new MessageDTO("Votação iniciada"));
	}

}
