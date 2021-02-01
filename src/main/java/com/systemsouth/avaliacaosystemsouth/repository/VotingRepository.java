package com.systemsouth.avaliacaosystemsouth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.systemsouth.avaliacaosystemsouth.domain.Topic;
import com.systemsouth.avaliacaosystemsouth.domain.User;
import com.systemsouth.avaliacaosystemsouth.domain.Voting;

public interface VotingRepository extends JpaRepository<Voting, Long> {

	Voting findByUserAndTopic(User user, Topic topic);


	@Query(value="select count(*) from voting where id_topic=:id and vote=:vote" , nativeQuery = true)
	int quantVote(Long id , Boolean vote);

}
