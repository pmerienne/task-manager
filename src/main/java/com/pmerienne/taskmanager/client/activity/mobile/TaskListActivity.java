package com.pmerienne.taskmanager.client.activity.mobile;

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.pmerienne.taskmanager.client.MobileClientFactory;
import com.pmerienne.taskmanager.client.utils.Services;
import com.pmerienne.taskmanager.client.view.mobile.TaskListView;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.Task;
import com.pmerienne.taskmanager.shared.model.TaskStatus;

public class TaskListActivity extends AbstractActivity implements TaskListView.Presenter {

	private final MobileClientFactory clientFactory;

	private TaskStatus taskStatus;

	private String projectId;

	public TaskListActivity(MobileClientFactory clientFactory, TaskStatus taskStatus) {
		this.clientFactory = clientFactory;
		this.taskStatus = taskStatus;
		this.projectId = null;
	}

	public TaskListActivity(MobileClientFactory clientFactory, String projectId, TaskStatus taskStatus) {
		this.clientFactory = clientFactory;
		this.taskStatus = taskStatus;
		this.projectId = projectId;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		TaskListView view = clientFactory.getTaskListView();
		view.setPresenter(this);
		view.setTaskStatus(this.taskStatus);
		panel.setWidget(view);
		loadTasks();
		loadProject();
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	private void loadTasks() {
		Services.taskService.find(this.projectId, this.taskStatus, new AsyncCallback<List<Task>>() {
			@Override
			public void onSuccess(List<Task> tasks) {
				clientFactory.getTaskListView().setTasks(tasks);
			}

			@Override
			public void onFailure(Throwable caught) {
				Dialogs.alert("Erreur", "Erreur lors du chargement des tâches : " + caught.getMessage(), null);
			}
		});

	}

	private void loadProject() {
		if (this.projectId != null && !this.projectId.isEmpty()) {
			Services.projectService.findById(this.projectId, new AsyncCallback<Project>() {
				@Override
				public void onFailure(Throwable caught) {
					Dialogs.alert("Erreur", "Erreur lors du chargement du projet : " + caught.getMessage(), null);
				}

				@Override
				public void onSuccess(Project project) {
					if (project != null) {
						clientFactory.getTaskListView().setProject(project);
					}
				}
			});
		}
	}

}
