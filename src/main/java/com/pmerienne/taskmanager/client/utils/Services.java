package com.pmerienne.taskmanager.client.utils;

import com.google.gwt.core.client.GWT;
import com.pmerienne.taskmanager.shared.service.ProjectService;
import com.pmerienne.taskmanager.shared.service.ProjectServiceAsync;
import com.pmerienne.taskmanager.shared.service.TaskService;
import com.pmerienne.taskmanager.shared.service.TaskServiceAsync;
import com.pmerienne.taskmanager.shared.service.UserService;
import com.pmerienne.taskmanager.shared.service.UserServiceAsync;

public class Services {

	public static final TaskServiceAsync taskService = GWT.create(TaskService.class);
	public static final UserServiceAsync userService = GWT.create(UserService.class);
	public static final ProjectServiceAsync projectService = GWT.create(ProjectService.class);
}
