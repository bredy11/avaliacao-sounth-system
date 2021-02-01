package com.systemsouth.avaliacaosystemsouth.api.dto;

import java.time.LocalDateTime;

import com.systemsouth.avaliacaosystemsouth.domain.Topic;

public class TopicDTO implements DTO {

	private Long id;
	private String text;
	private LocalDateTime votingStart;

	public TopicDTO(Topic topic) {
		this.id = topic.getId();
		this.text = topic.getText();
		this.votingStart = topic.getVotingStart();
	}

	public TopicDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDateTime getVotingStart() {
		return votingStart;
	}

	public void setVotingStart(LocalDateTime votingStart) {
		this.votingStart = votingStart;
	}
}
