package com.api.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.challenge.models.User;

public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findByUserName(String userName);
}
