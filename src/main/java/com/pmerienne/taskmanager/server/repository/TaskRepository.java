package com.pmerienne.taskmanager.server.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.Task;
import com.pmerienne.taskmanager.shared.model.TaskStatus;
import com.pmerienne.taskmanager.shared.model.User;

public interface TaskRepository extends MongoRepository<Task, String> {

	List<Task> findByStatus(TaskStatus status);

	List<Task> findByUserAndStatus(User user, TaskStatus status);

	List<Task> findByProjectAndStatus(Project project, TaskStatus status);

	List<Task> findByProject(Project project);

}
