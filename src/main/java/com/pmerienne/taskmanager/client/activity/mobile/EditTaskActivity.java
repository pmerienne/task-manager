package com.pmerienne.taskmanager.client.activity.mobile;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.pmerienne.taskmanager.client.MobileClientFactory;
import com.pmerienne.taskmanager.client.utils.Services;
import com.pmerienne.taskmanager.client.view.mobile.EditTaskView;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.Task;
import com.pmerienne.taskmanager.shared.model.TaskStatus;
import com.pmerienne.taskmanager.shared.model.User;

public class EditTaskActivity extends MobileActivity implements EditTaskView.Presenter {

	private final String taskId;
	private final TaskStatus status;

	public EditTaskActivity(MobileClientFactory clientFactory) {
		super(clientFactory);
		this.taskId = null;
		this.status = null;
	}

	public EditTaskActivity(MobileClientFactory clientFactory, TaskStatus status) {
		super(clientFactory);
		this.taskId = null;
		this.status = status;
	}

	public EditTaskActivity(MobileClientFactory clientFactory, String taskId) {
		super(clientFactory);
		this.taskId = taskId;
		this.status = null;
	}
	
	@Override
	protected IsWidget getView() {
		return this.clientFactory.getEditTaskView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		EditTaskView view = this.clientFactory.getEditTaskView();
		view.setPresenter(this);
		loadAvailableProjects();
		loadAvailableUsers();
		loadTask();
	}

	private void loadAvailableUsers() {
		Services.userService.findAll(new AsyncCallback<List<User>>() {
			@Override
			public void onFailure(Throwable caught) {
				Dialogs.alert("Erreur", "Erreur lors du chargement des utilisateurs : " + caught.getMessage(), null);
			}

			@Override
			public void onSuccess(List<User> users) {
				clientFactory.getEditTaskView().setAvailableUsers(users);
			}
		});
	}

	private void loadAvailableProjects() {
		Services.projectService.findAll(new AsyncCallback<List<Project>>() {
			@Override
			public void onFailure(Throwable caught) {
				Dialogs.alert("Erreur", "Erreur lors du chargement des projets : " + caught.getMessage(), null);
			}

			@Override
			public void onSuccess(List<Project> projects) {
				clientFactory.getEditTaskView().setAvailableProjects(projects);
			}
		});

	}

	private void loadTask() {
		if (this.taskId != null && !this.taskId.isEmpty()) {
			this.setPending(true);
			Services.taskService.findById(this.taskId, new AsyncCallback<Task>() {
				@Override
				public void onSuccess(Task task) {
					setPending(false);
					if (task == null) {
						if (status != null) {
							task = new Task(status);
							clientFactory.getEditTaskView().setTask(task);
						} else {
							task = new Task();
						}
					}
					clientFactory.getEditTaskView().setTask(task);
				}

				@Override
				public void onFailure(Throwable caught) {
					setPending(false);
					Dialogs.alert("Erreur", "Erreur lors du chargement de la tâche : " + caught.getMessage(), null);
				}
			});
		} else if (status != null) {
			Task task = new Task(this.status);
			this.clientFactory.getEditTaskView().setTask(task);
		} else {
			Task task = new Task();
			this.clientFactory.getEditTaskView().setTask(task);
		}
	}

	@Override
	public void save(Task task) {
		this.setPending(true);
		Services.taskService.save(task, new AsyncCallback<Task>() {
			@Override
			public void onFailure(Throwable caught) {
				setPending(false);
				Dialogs.alert("Erreur", "Erreur lors de la sauvegarde de la tâche : " + caught.getMessage(), null);
			}

			@Override
			public void onSuccess(Task task) {
				setPending(false);
				History.back();
			}
		});
	}

	@Override
	public void cancel() {
		History.back();
	}

}
