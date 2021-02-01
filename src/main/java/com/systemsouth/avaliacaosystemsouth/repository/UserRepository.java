package com.systemsouth.avaliacaosystemsouth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemsouth.avaliacaosystemsouth.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByDocument(String document);

}
