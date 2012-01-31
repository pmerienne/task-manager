package com.pmerienne.taskmanager.client.activity.desktop;

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.pmerienne.taskmanager.client.DesktopClientFactory;
import com.pmerienne.taskmanager.client.utils.Services;
import com.pmerienne.taskmanager.client.view.desktop.DashBoardView;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.Task;

public class DashBoardActivity extends AbstractActivity implements DashBoardView.Presenter {

	private DesktopClientFactory clientFactory;

	public DashBoardActivity(DesktopClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		DashBoardView view = this.clientFactory.getDashBoardView();
		view.setPresenter(this);
		panel.setWidget(view);
		loadAvailableProjects();
	}

	@Override
	public void loadTask(Project project) {
		Services.taskService.findByProject(project.getId(), new AsyncCallback<List<Task>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Impossible de charger les tâches du projet : " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<Task> tasks) {
				clientFactory.getDashBoardView().setTasks(tasks);
			}
		});
	}

	private void loadAvailableProjects() {
		Services.projectService.findAll(new AsyncCallback<List<Project>>() {
			@Override
			public void onSuccess(List<Project> projects) {
				clientFactory.getDashBoardView().setAvailableProjects(projects);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Impossible de charger le projet : " + caught.getMessage());
			}
		});
	}
}
