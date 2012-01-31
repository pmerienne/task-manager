package com.pmerienne.taskmanager.client.activity.mobile;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.pmerienne.taskmanager.client.MobileClientFactory;
import com.pmerienne.taskmanager.client.utils.Services;
import com.pmerienne.taskmanager.client.view.mobile.TaskDetailView;
import com.pmerienne.taskmanager.shared.model.Task;

public class TaskDetailActivity extends AbstractActivity implements TaskDetailView.Presenter {

	private MobileClientFactory clientFactory;
	private String taskId;

	public TaskDetailActivity(MobileClientFactory clientFactory, String taskId) {
		this.clientFactory = clientFactory;
		this.taskId = taskId;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		TaskDetailView taskDetailView = this.clientFactory.getTaskDetailView();
		taskDetailView.setPresenter(this);
		panel.setWidget(taskDetailView);
		loadTask(this.taskId);
	}

	@Override
	public void goTo(Place place) {
		this.clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void save(Task task) {
		Services.taskService.save(task, new AsyncCallback<Task>() {
			@Override
			public void onSuccess(Task result) {
				clientFactory.getTaskDetailView().setTask(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Dialogs.alert("Erreur", "Erreur lors de la sauvegarde de la tâche : " + caught.getMessage(), null);
			}
		});
	}

	@Override
	public void delete(Task task) {
		Services.taskService.delete(task, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Dialogs.alert("Erreur", "Erreur lors de la suppression de la tâche : " + caught.getMessage(), null);
			}

			@Override
			public void onSuccess(Void result) {
				Dialogs.alert("Information", "La tâche a été supprimé", null);
				History.back();
			}
		});
	}

	private void loadTask(String taskId) {
		Services.taskService.findById(taskId, new AsyncCallback<Task>() {
			@Override
			public void onFailure(Throwable caught) {
				Dialogs.alert("Erreur", "Erreur lors du chargement de la tâche : " + caught.getMessage(), null);
			}

			@Override
			public void onSuccess(Task task) {
				clientFactory.getTaskDetailView().setTask(task);
			}
		});
	}
}
