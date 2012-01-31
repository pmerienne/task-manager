package com.pmerienne.taskmanager.shared.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pmerienne.taskmanager.shared.model.Task;
import com.pmerienne.taskmanager.shared.model.TaskStatus;

@RemoteServiceRelativePath("taskService.rpc")
public interface TaskService extends RemoteService {

	Task save(Task task);

	void delete(Task task);

	List<Task> find(String projectId, TaskStatus status);

	Task findById(String taskId);

	List<Task> findByProject(String projectId);
}
