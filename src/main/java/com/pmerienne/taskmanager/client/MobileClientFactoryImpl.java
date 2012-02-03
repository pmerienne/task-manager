/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.pmerienne.taskmanager.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.pmerienne.taskmanager.client.view.mobile.EditProjectView;
import com.pmerienne.taskmanager.client.view.mobile.EditProjectViewImpl;
import com.pmerienne.taskmanager.client.view.mobile.EditTaskView;
import com.pmerienne.taskmanager.client.view.mobile.EditTaskViewImpl;
import com.pmerienne.taskmanager.client.view.mobile.HomeView;
import com.pmerienne.taskmanager.client.view.mobile.HomeViewImpl;
import com.pmerienne.taskmanager.client.view.mobile.PendingView;
import com.pmerienne.taskmanager.client.view.mobile.PendingViewImpl;
import com.pmerienne.taskmanager.client.view.mobile.ProjectDetailView;
import com.pmerienne.taskmanager.client.view.mobile.ProjectDetailViewImpl;
import com.pmerienne.taskmanager.client.view.mobile.ProjectListView;
import com.pmerienne.taskmanager.client.view.mobile.ProjectListViewImpl;
import com.pmerienne.taskmanager.client.view.mobile.RegisterView;
import com.pmerienne.taskmanager.client.view.mobile.RegisterViewImpl;
import com.pmerienne.taskmanager.client.view.mobile.TaskDetailView;
import com.pmerienne.taskmanager.client.view.mobile.TaskDetailViewImpl;
import com.pmerienne.taskmanager.client.view.mobile.TaskListView;
import com.pmerienne.taskmanager.client.view.mobile.TaskListViewImpl;
import com.pmerienne.taskmanager.client.view.mobile.TaskStatusView;
import com.pmerienne.taskmanager.client.view.mobile.TaskStatusViewImpl;

public class MobileClientFactoryImpl implements MobileClientFactory {

	private final EventBus eventBus = new SimpleEventBus();
	@SuppressWarnings("deprecation")
	private final PlaceController placeController = new PlaceController(eventBus);

	private final HomeView homeView = new HomeViewImpl();
	private final TaskStatusView taskSatusView = new TaskStatusViewImpl();
	private final TaskListView taskListView = new TaskListViewImpl();
	private final TaskDetailView taskDetailView = new TaskDetailViewImpl();
	private final EditTaskView editTaskView = new EditTaskViewImpl();
	private final ProjectListView projectListView = new ProjectListViewImpl();
	private final EditProjectView editProjectView = new EditProjectViewImpl();
	private final ProjectDetailView projectDetailView = new ProjectDetailViewImpl();
	private final RegisterView registerView = new RegisterViewImpl();
	private final PendingView pendingView = new PendingViewImpl();

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public TaskStatusView getTaskStatusView() {
		return taskSatusView;
	}

	@Override
	public TaskListView getTaskListView() {
		return taskListView;
	}

	@Override
	public TaskDetailView getTaskDetailView() {
		return taskDetailView;
	}

	@Override
	public EditTaskView getEditTaskView() {
		return editTaskView;
	}

	@Override
	public HomeView getHomeView() {
		return homeView;
	}

	@Override
	public ProjectListView getProjectListView() {
		return projectListView;
	}

	@Override
	public EditProjectView getEditProjectView() {
		return editProjectView;
	}

	@Override
	public ProjectDetailView getProjectDetailView() {
		return projectDetailView;
	}
	
	@Override
	public RegisterView getRegisterView() {
		return registerView;
	}
	
	@Override
	public PendingView getPendingView() {
		return pendingView;
	}
}
