package com.systemsouth.avaliacaosystemsouth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.systemsouth.avaliacaosystemsouth.domain.Schedule;
import com.systemsouth.avaliacaosystemsouth.domain.User;
import com.systemsouth.avaliacaosystemsouth.domain.Voting;

public interface VotingRepository extends JpaRepository<Voting, Long> {

	Voting findByUserAndSchedule(User user, Schedule schedule);


	@Query(value="select count(*) from voting where id_schedule=:id and vote=:vote" , nativeQuery = true)
	int quantVote(Long id , Boolean vote);

}
