package com.systemsouth.avaliacaosystemsouth.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemsouth.avaliacaosystemsouth.api.dto.TopicDTO;
import com.systemsouth.avaliacaosystemsouth.domain.Topic;
import com.systemsouth.avaliacaosystemsouth.exception.handler.VotingNotStartedException;
import com.systemsouth.avaliacaosystemsouth.repository.TopicRepository;

@Service
public class TopicService {

	@Autowired
	private TopicRepository repository;

	public List<TopicDTO> getTopic() {
		List<Topic> list = repository.findAll();

		List<TopicDTO> topicDTOs = new ArrayList<TopicDTO>();
		list.stream().forEach(topic -> {
			topicDTOs.add(new TopicDTO(topic));
		});

		return topicDTOs;
	}

	public TopicDTO creatTopic(TopicDTO topicDTO) {

		Topic topic = new Topic();
		topic.setText(topicDTO.getText());

		topic = repository.save(topic);
		return new TopicDTO(topic);
	}

	public TopicDTO updateTopic(TopicDTO topicDTO) {
		Topic topic = new Topic();
		topic.setId(topicDTO.getId());
		topic.setText(topicDTO.getText());
		topic.setVotingStart(topicDTO.getVotingStart());

		topic = repository.save(topic);
		return new TopicDTO(topic);
	}

	public void deleteTopic(Long id) {
		Topic topic = new Topic();
		topic.setId(id);

		repository.delete(topic);
	}

	public void startTopic(Long id) {
		Topic topic = repository.findById(id).get();
		if(topic.getVotingStart() == null) {
			topic.setVotingStart(LocalDateTime.now());
		}else {
			throw new VotingNotStartedException("Votação ja foi iniciada");	
		}
		
		repository.save(topic);
	}

	public Topic findById(Long id) {
		return repository.findById(id).get();
	}

}
