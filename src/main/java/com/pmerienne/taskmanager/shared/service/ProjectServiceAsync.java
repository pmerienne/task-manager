package com.pmerienne.taskmanager.shared.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.ProjectStatistics;

public interface ProjectServiceAsync {

	void delete(Project project, AsyncCallback<Void> callback);

	void findById(String projectId, AsyncCallback<Project> callback);

	void save(Project project, AsyncCallback<Project> callback);

	void findAll(AsyncCallback<List<Project>> callback);

	void getProjectStatistics(String projectId, AsyncCallback<ProjectStatistics> callback);

}
