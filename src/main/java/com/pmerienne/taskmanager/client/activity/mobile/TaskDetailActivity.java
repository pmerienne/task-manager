package com.pmerienne.taskmanager.client.activity.mobile;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.pmerienne.taskmanager.client.MobileClientFactory;
import com.pmerienne.taskmanager.client.utils.Services;
import com.pmerienne.taskmanager.client.view.mobile.TaskDetailView;
import com.pmerienne.taskmanager.shared.model.Task;

public class TaskDetailActivity extends MobileActivity implements TaskDetailView.Presenter {

	private String taskId;

	public TaskDetailActivity(MobileClientFactory clientFactory, String taskId) {
		super(clientFactory);
		this.taskId = taskId;
	}

	@Override
	protected IsWidget getView() {
		return this.clientFactory.getTaskDetailView();
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		TaskDetailView taskDetailView = this.clientFactory.getTaskDetailView();
		taskDetailView.setPresenter(this);
		loadTask(this.taskId);
	}

	@Override
	public void goTo(Place place) {
		this.clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void save(Task task) {
		this.setPending(true);
		Services.taskService.save(task, new AsyncCallback<Task>() {
			@Override
			public void onSuccess(Task result) {
				setPending(false);
				clientFactory.getTaskDetailView().setTask(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				setPending(false);
				Dialogs.alert("Erreur", "Erreur lors de la sauvegarde de la tâche : " + caught.getMessage(), null);
			}
		});
	}

	@Override
	public void delete(Task task) {
		this.setPending(true);
		Services.taskService.delete(task, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				setPending(false);
				Dialogs.alert("Erreur", "Erreur lors de la suppression de la tâche : " + caught.getMessage(), null);
			}

			@Override
			public void onSuccess(Void result) {
				setPending(false);
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
