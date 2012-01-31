package com.pmerienne.taskmanager.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmerienne.taskmanager.server.messaging.MessagingService;
import com.pmerienne.taskmanager.server.repository.ProjectRepository;
import com.pmerienne.taskmanager.server.repository.TaskRepository;
import com.pmerienne.taskmanager.server.repository.UserRepository;
import com.pmerienne.taskmanager.server.security.LoggedUser;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.Task;
import com.pmerienne.taskmanager.shared.model.TaskStatus;
import com.pmerienne.taskmanager.shared.model.User;
import com.pmerienne.taskmanager.shared.service.TaskService;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	MessagingService messagingService;

	@Override
	public Task save(Task task) {
		User user = this.checkUser();
		task.setUser(user);
		this.taskRepository.save(task);
		this.messagingService.onTaskSaved(task);
		return task;
	}

	@Override
	public void delete(Task task) {
		User user = this.checkUser();
		if (user.equals(task.getUser())) {
			this.taskRepository.delete(task);
		} else {
			throw new IllegalAccessError("User doesn't have the right");
		}
	}

	@Override
	public List<Task> find(String projectId, TaskStatus status) {
		User user = this.checkUser();
		if (projectId != null && !projectId.isEmpty()) {
			Project project = this.projectRepository.findOne(projectId);
			if (project == null) {
				throw new IllegalArgumentException("Project with id " + projectId + " doesn't exists");
			}
			if (!project.getUsers().contains(user)) {
				throw new IllegalArgumentException("Pas le droit !");
			}
			return this.taskRepository.findByProjectAndStatus(project, status);
		} else {
			return this.taskRepository.findByUserAndStatus(user, status);
		}
	}

	@Override
	public List<Task> findByProject(String projectId) {
		User user = this.checkUser();
		Project project = this.projectRepository.findOne(projectId);
		if (project == null) {
			throw new IllegalArgumentException("Project with id " + projectId + " doesn't exists");
		}
		if (!project.getUsers().contains(user)) {
			throw new IllegalArgumentException("No right to read project");
		}
		return this.taskRepository.findByProject(project);
	}

	private User checkUser() {
		// Set/Check user
		User user = this.userRepository.findByLogin(LoggedUser.getLogin());
		if (user == null) {
			throw new IllegalAccessError("User must be logged");
		}
		return user;
	}

	@Override
	public Task findById(String taskId) {
		return this.taskRepository.findOne(taskId);
	}

}
