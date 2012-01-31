package com.pmerienne.taskmanager.shared.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.ProjectStatistics;

@RemoteServiceRelativePath("projectService.rpc")
public interface ProjectService extends RemoteService {

	Project save(Project project);

	void delete(Project project);

	Project findById(String projectId);
	
	List<Project> findAll();
	
	ProjectStatistics getProjectStatistics(String projectId);
}
