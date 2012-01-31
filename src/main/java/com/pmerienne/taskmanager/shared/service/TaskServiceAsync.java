package com.pmerienne.taskmanager.shared.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pmerienne.taskmanager.shared.model.Task;
import com.pmerienne.taskmanager.shared.model.TaskStatus;

public interface TaskServiceAsync {

	void delete(Task task, AsyncCallback<Void> callback);

	void save(Task task, AsyncCallback<Task> callback);

	void findById(String taskId, AsyncCallback<Task> callback);

	void find(String projectId, TaskStatus status, AsyncCallback<List<Task>> callback);

	void findByProject(String projectId, AsyncCallback<List<Task>> callback);

}
