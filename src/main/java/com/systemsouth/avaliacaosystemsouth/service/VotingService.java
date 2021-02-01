package com.systemsouth.avaliacaosystemsouth.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.systemsouth.avaliacaosystemsouth.api.dto.MessageDTO;
import com.systemsouth.avaliacaosystemsouth.api.dto.ValidateDocumentResponseDTO;
import com.systemsouth.avaliacaosystemsouth.api.dto.VotingDTO;
import com.systemsouth.avaliacaosystemsouth.api.enuns.StatusValideteDocument;
import com.systemsouth.avaliacaosystemsouth.domain.Schedule;
import com.systemsouth.avaliacaosystemsouth.domain.User;
import com.systemsouth.avaliacaosystemsouth.domain.Voting;
import com.systemsouth.avaliacaosystemsouth.exception.handler.InvalidDocumentException;
import com.systemsouth.avaliacaosystemsouth.exception.handler.VotingNotStartedException;
import com.systemsouth.avaliacaosystemsouth.facade.IntegrationValidateDocumentImpl;
import com.systemsouth.avaliacaosystemsouth.repository.VotingRepository;

@Service
public class VotingService {

	@Autowired
	private UserService userService;

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private VotingRepository votingRepository;

	@Autowired
	private IntegrationValidateDocumentImpl integrationValidateDocument;

	@Value("${south.system.url.validate.voting.time.minutes}")
	private Long durationMax;

	public MessageDTO vote(VotingDTO votingDTO) throws Exception {
		Schedule schedule = scheduleService.findById(votingDTO.getIdSchedule());

		if (valideteTimeVoting(schedule.getVotingStart())) {
			return new MessageDTO(getResultVoting(schedule));
		}

		User user = userService.findbyDocument(votingDTO.getDocument());
		if (user == null) {
			user = createUser(votingDTO);
		}

		return creatVote(votingDTO, user, schedule);
	}

	private String getResultVoting(Schedule schedule) {

		int voteNegative = votingRepository.quantVote(schedule.getId(), false);
		int votePositive = votingRepository.quantVote(schedule.getId(), true);

		return String.format("Tempo de votação esgotado. Resultado da votação: sim: %d   não: %d",  voteNegative, votePositive);
	}

	private boolean valideteTimeVoting(LocalDateTime votingStart) {
		if (votingStart == null) {
			throw new VotingNotStartedException("Votação não iniciada");
		}

		LocalDateTime now = LocalDateTime.now();
		Duration duration = Duration.between(votingStart, now);

		return duration.toMinutes() > durationMax ? true : false;
	}

	private MessageDTO creatVote(VotingDTO votingDTO, User user, Schedule schedule) {

		Voting voting = votingRepository.findByUserAndSchedule(user, schedule);
		if (voting != null) {
			return new MessageDTO("Usuario ja voto nessa pauta");
		}

		voting = new Voting();
		voting.setDateVoting(LocalDateTime.now());
		voting.setSchedule(schedule);
		voting.setUser(user);
		voting.setVote(votingDTO.getVote().equalsIgnoreCase("SIM") ? Boolean.TRUE : Boolean.FALSE);
		votingRepository.save(voting);
		return new MessageDTO("Seu voto foi aprovado");

	}

	private User createUser(VotingDTO votingDTO) throws Exception {
		ValidateDocumentResponseDTO validateDocument = integrationValidateDocument
				.validateDocument(votingDTO.getDocument());
		if (StatusValideteDocument.ABLE_TO_VOTE == validateDocument.getStatus()) {
			return userService.creatUser(votingDTO.getDocument());
		} else {
			throw new InvalidDocumentException("Cpf invalido");
		}
	}

	public void voteCount() {

	}

}
