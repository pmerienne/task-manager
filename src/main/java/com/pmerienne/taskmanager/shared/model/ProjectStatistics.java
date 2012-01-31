package com.pmerienne.taskmanager.shared.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ProjectStatistics implements Serializable {

	private static final long serialVersionUID = 1500228902754285700L;

	private String projectId;

	private Map<TaskStatus, Integer> tasksCount = new HashMap<TaskStatus, Integer>();

	public ProjectStatistics() {
	}

	public ProjectStatistics(String projectId) {
		super();
		this.projectId = projectId;
	}

	public ProjectStatistics(String projectId, Map<TaskStatus, Integer> tasksCount) {
		super();
		this.projectId = projectId;
		this.tasksCount = tasksCount;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Map<TaskStatus, Integer> getTasksCount() {
		return tasksCount;
	}

	public void setTasksCount(Map<TaskStatus, Integer> tasksCount) {
		this.tasksCount = tasksCount;
	}

	@Override
	public String toString() {
		return "ProjectStatistics [projectId=" + projectId + ", tasksCount=" + tasksCount + "]";
	}

}
