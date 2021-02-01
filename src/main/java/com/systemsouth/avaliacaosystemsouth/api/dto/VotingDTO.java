package com.systemsouth.avaliacaosystemsouth.api.dto;

public class VotingDTO {

	 
	private String vote;
	private String document;
	private Long idTopic;
	
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
	public Long getIdTopic() {
		return idTopic;
	}
	public void setIdTopic(Long idTopic) {
		this.idTopic = idTopic;
	}
	 
}
