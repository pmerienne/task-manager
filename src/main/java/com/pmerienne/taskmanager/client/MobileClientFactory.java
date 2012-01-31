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

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.pmerienne.taskmanager.client.view.mobile.EditProjectView;
import com.pmerienne.taskmanager.client.view.mobile.EditTaskView;
import com.pmerienne.taskmanager.client.view.mobile.HomeView;
import com.pmerienne.taskmanager.client.view.mobile.ProjectDetailView;
import com.pmerienne.taskmanager.client.view.mobile.ProjectListView;
import com.pmerienne.taskmanager.client.view.mobile.TaskDetailView;
import com.pmerienne.taskmanager.client.view.mobile.TaskListView;
import com.pmerienne.taskmanager.client.view.mobile.TaskStatusView;

public interface MobileClientFactory {

	EventBus getEventBus();

	PlaceController getPlaceController();

	HomeView getHomeView();

	TaskStatusView getTaskStatusView();

	TaskListView getTaskListView();

	TaskDetailView getTaskDetailView();

	EditTaskView getEditTaskView();

	ProjectListView getProjectListView();

	EditProjectView getEditProjectView();

	ProjectDetailView getProjectDetailView();
}
