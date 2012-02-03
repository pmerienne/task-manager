package com.pmerienne.taskmanager.client.activity.mobile;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.pmerienne.taskmanager.client.MobileClientFactory;
import com.pmerienne.taskmanager.client.place.mobile.EditProjectPlace;
import com.pmerienne.taskmanager.client.place.mobile.EditTaskPlace;
import com.pmerienne.taskmanager.client.place.mobile.HomePlace;
import com.pmerienne.taskmanager.client.place.mobile.ProjectDetailPlace;
import com.pmerienne.taskmanager.client.place.mobile.ProjectListPlace;
import com.pmerienne.taskmanager.client.place.mobile.RegisterPlace;
import com.pmerienne.taskmanager.client.place.mobile.TaskDetailPlace;
import com.pmerienne.taskmanager.client.place.mobile.TaskListPlace;
import com.pmerienne.taskmanager.client.place.mobile.TaskStatusPlace;
import com.pmerienne.taskmanager.client.utils.StringUtils;

public class MobileActivityMapper implements ActivityMapper {

	private final MobileClientFactory clientFactory;

	public MobileActivityMapper(MobileClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if(place instanceof RegisterPlace) {
			// Register
			return new RegisterActivity(clientFactory);
		}else if (place instanceof HomePlace) {
			// Home
			return new HomeActivity(clientFactory);
		} else if (place instanceof ProjectListPlace) {
			// Project list
			return new ProjectListActivity(clientFactory);
		} else if (place instanceof ProjectDetailPlace) {
			// Project list
			return new ProjectDetailActivity(clientFactory, ((ProjectDetailPlace) place).getProjectId());
		} else if (place instanceof EditProjectPlace) {
			// Edit project
			String projectId = ((EditProjectPlace) place).getProjectId();
			if (projectId != null && !projectId.isEmpty()) {
				return new EditProjectActivity(clientFactory, projectId);
			} else {
				return new EditProjectActivity(clientFactory);
			}
		} else if (place instanceof TaskStatusPlace) {
			// Task status list
			String projectId = ((TaskStatusPlace) place).getProjectId();
			if (StringUtils.isEmpty(projectId)) {
				return new TaskStatusActivity(clientFactory);
			} else {
				return new TaskStatusActivity(clientFactory, projectId);
			}
		} else if (place instanceof TaskListPlace) {
			// Task list
			TaskListPlace taskListPlace = (TaskListPlace) place;
			if (taskListPlace.getProjectId() != null && !taskListPlace.getProjectId().isEmpty()) {
				return new TaskListActivity(clientFactory, taskListPlace.getProjectId(), taskListPlace.getStatus());
			} else {
				return new TaskListActivity(clientFactory, taskListPlace.getStatus());
			}
		} else if (place instanceof TaskDetailPlace) {
			// Task detail
			return new TaskDetailActivity(clientFactory, ((TaskDetailPlace) place).getTaskId());
		} else if (place instanceof EditTaskPlace) {
			// Edit task
			EditTaskPlace editTaskPlace = (EditTaskPlace) place;
			if (editTaskPlace.getStatus() != null) {
				return new EditTaskActivity(clientFactory, editTaskPlace.getStatus());
			} else if (editTaskPlace.getTaskId() != null && !editTaskPlace.getTaskId().isEmpty()) {
				return new EditTaskActivity(clientFactory, editTaskPlace.getTaskId());
			} else {
				return new EditTaskActivity(clientFactory);
			}
		}
		return new HomeActivity(clientFactory);
	}

}
