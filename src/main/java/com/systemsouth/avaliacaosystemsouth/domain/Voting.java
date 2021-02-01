package com.systemsouth.avaliacaosystemsouth.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VOTING")
public class Voting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
    @JoinColumn(name = "id_user")
	private User user;
	
	@OneToOne
    @JoinColumn(name = "id_topic")
	private Topic topic;
	
	private Boolean vote;
	private LocalDateTime dateVoting;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

 

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getDateVoting() {
		return dateVoting;
	}

	public void setDateVoting(LocalDateTime dateVoting) {
		this.dateVoting = dateVoting;
	}

	public Boolean getVote() {
		return vote;
	}

	public void setVote(Boolean vote) {
		this.vote = vote;
	}

}
