package com.systemsouth.avaliacaosystemsouth.api.dto;

import java.time.LocalDateTime;

import com.systemsouth.avaliacaosystemsouth.domain.Schedule;

public class ScheduleDTO implements DTO {

	private Long id;
	private String text;
	private LocalDateTime votingStart;

	public ScheduleDTO(Schedule schedule) {
		this.id = schedule.getId();
		this.text = schedule.getText();
		this.votingStart = schedule.getVotingStart();
	}

	public ScheduleDTO() {
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
