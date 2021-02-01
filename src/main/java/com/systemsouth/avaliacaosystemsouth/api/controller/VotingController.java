package com.systemsouth.avaliacaosystemsouth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.systemsouth.avaliacaosystemsouth.api.dto.MessageDTO;
import com.systemsouth.avaliacaosystemsouth.api.dto.VotingDTO;
import com.systemsouth.avaliacaosystemsouth.service.UserService;
import com.systemsouth.avaliacaosystemsouth.service.VotingService;

@RestController
@RequestMapping(value = "/v1/voting")
public class VotingController {

	@Autowired
	private VotingService votingService;

	@PostMapping
	public ResponseEntity<MessageDTO> polling(@RequestBody VotingDTO votingDTO) throws Exception {
		return ResponseEntity.ok().body(votingService.vote(votingDTO));
	}

}
