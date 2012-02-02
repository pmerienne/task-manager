package com.pmerienne.taskmanager.client.activity.desktop;

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.pmerienne.taskmanager.client.DesktopClientFactory;
import com.pmerienne.taskmanager.client.utils.Services;
import com.pmerienne.taskmanager.client.view.desktop.DashBoardView;
import com.pmerienne.taskmanager.shared.messaging.event.TaskSavedEvent;
import com.pmerienne.taskmanager.shared.messaging.event.TaskSavedHandler;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.Task;
import com.pmerienne.taskmanager.shared.model.User;

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
		Services.userService.getCurrentUser(new AsyncCallback<User>() {
			@Override
			public void onFailure(Throwable caught) {
				clientFactory.getDashBoardView().setLogged(false);
			}

			@Override
			public void onSuccess(User user) {
				if (user != null) {
					clientFactory.getDashBoardView().setLogged(true);
					loadAvailableProjects();
					bind();
				} else {
					clientFactory.getDashBoardView().setLogged(false);
				}

			}
		});
	}

	private boolean binded = false;

	private void bind() {
		if (!binded) {
			this.clientFactory.getEventBus().addHandler(TaskSavedEvent.getType(), new TaskSavedHandler() {
				@Override
				public void onTaskSaved(TaskSavedEvent event) {
					clientFactory.getDashBoardView().updateTask(event.getTask());
				}
			});
			this.binded = true;
		}
	};

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

	@Override
	public void goTo(Place place) {
		this.clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void login(String login, String password) {
		Services.userService.login(login, password, new AsyncCallback<User>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Impossible de se connecter à l'application : " + caught.getMessage());
				clientFactory.getDashBoardView().setLogged(false);
			}

			@Override
			public void onSuccess(User user) {
				if (user != null) {
					clientFactory.getDashBoardView().setLogged(true);
					loadAvailableProjects();
					bind();
				} else {
					Window.alert("Mauvais login/mot de passe");
					clientFactory.getDashBoardView().setLogged(false);
				}
			}
		});
	}

	@Override
	public void logout() {
		Services.userService.logout(new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				clientFactory.getDashBoardView().setLogged(false);
			}

			@Override
			public void onSuccess(Void result) {
				clientFactory.getDashBoardView().setLogged(false);
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
