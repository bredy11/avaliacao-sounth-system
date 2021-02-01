package com.systemsouth.avaliacaosystemsouth.api.dto;

public class VotingDTO {

	private String vote;
	private String document;
	private Long idSchedule;
	
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getVote() {
		return vote;
	}
	public void setVote(String vote) {
		this.vote = vote;
	}
	public Long getIdSchedule() {
		return idSchedule;
	}
	public void setIdSchedule(Long idSchedule) {
		this.idSchedule = idSchedule;
	}
	
}
