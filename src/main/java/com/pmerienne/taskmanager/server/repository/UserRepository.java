package com.pmerienne.taskmanager.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pmerienne.taskmanager.shared.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByLogin(String login);
}