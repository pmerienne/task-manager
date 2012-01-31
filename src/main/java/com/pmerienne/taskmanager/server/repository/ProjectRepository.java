package com.pmerienne.taskmanager.server.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.User;

public interface ProjectRepository extends MongoRepository<Project, String> {

	List<Project> findByUsers(User user);
}
