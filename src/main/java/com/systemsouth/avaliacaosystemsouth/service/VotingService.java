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
import com.systemsouth.avaliacaosystemsouth.domain.Topic;
import com.systemsouth.avaliacaosystemsouth.domain.User;
import com.systemsouth.avaliacaosystemsouth.domain.Voting;
import com.systemsouth.avaliacaosystemsouth.exception.handler.InvalidDocumentException;
import com.systemsouth.avaliacaosystemsouth.exception.handler.RequestInvalidaException;
import com.systemsouth.avaliacaosystemsouth.exception.handler.VotingNotStartedException;
import com.systemsouth.avaliacaosystemsouth.facade.IntegrationValidateDocumentImpl;
import com.systemsouth.avaliacaosystemsouth.repository.VotingRepository;

@Service
public class VotingService {

	@Autowired
	private UserService userService;

	@Autowired
	private TopicService topicService;

	@Autowired
	private VotingRepository votingRepository;

	@Autowired
	private IntegrationValidateDocumentImpl integrationValidateDocument;

	@Value("${south.system.url.validate.voting.time.minutes}")
	private Long durationMax;

	/**
	 * Cria um voto
	 * 
	 * @param votingDTO
	 * @return
	 * @throws Exception
	 */
	public MessageDTO vote(VotingDTO votingDTO) throws Exception {
		validRequest(votingDTO);
		Topic topic = topicService.findById(votingDTO.getIdTopic());

		if (valideteTimeVoting(topic.getVotingStart())) {
			return new MessageDTO(getResultVoting(topic));
		}

		User user = userService.findbyDocument(votingDTO.getDocument());
		if (user == null) {
			user = createUser(votingDTO);
		}

		return creatVote(votingDTO, user, topic);
	}

	/**
	 * Valida se o parametros passados esta respeitando as regras da votação
	 * 
	 * @param votingDTO
	 */
	private void validRequest(VotingDTO votingDTO) {
		if (!votingDTO.getVote().equalsIgnoreCase("Sim") && !votingDTO.getVote().equalsIgnoreCase("Não")) {
			throw new RequestInvalidaException("Request com dados invalidos");
		}
		if (votingDTO.getDocument() == null) {
			throw new RequestInvalidaException("Request com dados invalidos");
		}
	}

	private String getResultVoting(Topic topic) {

		int voteNegative = votingRepository.quantVote(topic.getId(), false);
		int votePositive = votingRepository.quantVote(topic.getId(), true);

		return String.format("Tempo de votação esgotado. Resultado da votação: sim: %d   não: %d", voteNegative,
				votePositive);
	}

	/**
	 * Verifica se a votação esta dentro do prazo determinado, a variavel
	 * "durationMax" é definida por variavel ambiente
	 * 
	 * @param votingStart
	 * @return
	 */
	private boolean valideteTimeVoting(LocalDateTime votingStart) {
		if (votingStart == null) {
			throw new VotingNotStartedException("Votação não iniciada");
		}

		LocalDateTime now = LocalDateTime.now();
		Duration duration = Duration.between(votingStart, now);

		return duration.toMinutes() > durationMax ? true : false;
	}

	/**
	 * Cria um voto caso o usuario não tenha votado ainda na pauta
	 * 
	 * @param votingDTO
	 * @param user
	 * @param topic
	 * @return
	 */
	private MessageDTO creatVote(VotingDTO votingDTO, User user, Topic topic) {

		Voting voting = votingRepository.findByUserAndTopic(user, topic);
		if (voting != null) {
			return new MessageDTO("Usuario ja voto nessa pauta");
		}

		voting = new Voting();
		voting.setDateVoting(LocalDateTime.now());
		voting.setTopic(topic);
		voting.setUser(user);
		voting.setVote(votingDTO.getVote().equalsIgnoreCase("SIM") ? Boolean.TRUE : Boolean.FALSE);
		votingRepository.save(voting);
		return new MessageDTO("Seu voto foi aprovado");

	}

	/**
	 * É feito uma integração com o serviço para validar se o cpf esta valido, foi
	 * visto no desenvovimento que o serviço esta com uma intermitencia e as vezes
	 * pode mostra uma resposta que não é correta
	 * 
	 * @param votingDTO
	 * @return
	 * @throws Exception
	 */
	private User createUser(VotingDTO votingDTO) throws Exception {
		ValidateDocumentResponseDTO validateDocument = integrationValidateDocument
				.validateDocument(votingDTO.getDocument());
		if (StatusValideteDocument.ABLE_TO_VOTE == validateDocument.getStatus()) {
			return userService.creatUser(votingDTO.getDocument());
		} else {
			throw new InvalidDocumentException("Cpf invalido");
		}
	}

	 

}
