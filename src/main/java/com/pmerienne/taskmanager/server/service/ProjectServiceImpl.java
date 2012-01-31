package com.pmerienne.taskmanager.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmerienne.taskmanager.server.repository.ProjectRepository;
import com.pmerienne.taskmanager.server.repository.TaskRepository;
import com.pmerienne.taskmanager.server.repository.UserRepository;
import com.pmerienne.taskmanager.server.security.LoggedUser;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.ProjectStatistics;
import com.pmerienne.taskmanager.shared.model.Task;
import com.pmerienne.taskmanager.shared.model.TaskStatus;
import com.pmerienne.taskmanager.shared.model.User;
import com.pmerienne.taskmanager.shared.service.ProjectService;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	TaskRepository taskRepository;

	@Override
	public Project save(Project project) {
		User user = this.checkUser();
		if (!project.getUsers().contains(user)) {
			project.getUsers().add(user);
		}

		return this.projectRepository.save(project);
	}

	@Override
	public void delete(Project project) {
		this.projectRepository.delete(project);
	}

	@Override
	public Project findById(String projectId) {
		return this.projectRepository.findOne(projectId);
	}

	@Override
	public List<Project> findAll() {
		User user = this.checkUser();
		return this.projectRepository.findByUsers(user);
	}

	@Override
	public ProjectStatistics getProjectStatistics(String projectId) {
		// On récupère les tâches du projet
		Project project = this.findById(projectId);
		if (project == null) {
			throw new IllegalArgumentException("Impossible de trouver le projet " + projectId);
		}
		List<Task> tasks = this.taskRepository.findByProject(project);
		// On compte !
		int todoCount = 0;
		int doingCount = 0;
		int doneCount = 0;
		int archivedCount = 0;
		for (Task task : tasks) {
			switch (task.getStatus()) {
			case TODO:
				todoCount++;
				break;
			case DOING:
				doingCount++;
				break;
			case DONE:
				doneCount++;
				break;
			case ARCHIVED:
				archivedCount++;
				break;
			}
		}
		// On construit le bean contenant les infos
		ProjectStatistics stats = new ProjectStatistics(projectId);
		stats.getTasksCount().put(TaskStatus.TODO, todoCount);
		stats.getTasksCount().put(TaskStatus.DOING, doingCount);
		stats.getTasksCount().put(TaskStatus.DONE, doneCount);
		stats.getTasksCount().put(TaskStatus.ARCHIVED, archivedCount);
		return stats;
	}

	private User checkUser() {
		// Set/Check user
		User user = this.userRepository.findByLogin(LoggedUser.getLogin());
		if (user == null) {
			throw new IllegalAccessError("User must be logged");
		}
		return user;
	}
}
