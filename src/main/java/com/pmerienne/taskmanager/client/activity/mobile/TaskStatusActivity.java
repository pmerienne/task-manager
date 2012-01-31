package com.pmerienne.taskmanager.client.activity.mobile;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.pmerienne.taskmanager.client.MobileClientFactory;
import com.pmerienne.taskmanager.client.utils.Services;
import com.pmerienne.taskmanager.client.utils.StringUtils;
import com.pmerienne.taskmanager.client.view.mobile.TaskStatusView;
import com.pmerienne.taskmanager.shared.model.Project;

public class TaskStatusActivity extends AbstractActivity implements TaskStatusView.Presenter {

	private final MobileClientFactory clientFactory;
	private String projectId;

	public TaskStatusActivity(MobileClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	public TaskStatusActivity(MobileClientFactory clientFactory, String projectId) {
		this.clientFactory = clientFactory;
		this.projectId = projectId;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		TaskStatusView view = clientFactory.getTaskStatusView();
		view.setPresenter(this);
		if (!StringUtils.isEmpty(this.projectId)) {
			Services.projectService.findById(this.projectId, new AsyncCallback<Project>() {
				@Override
				public void onSuccess(Project project) {
					clientFactory.getTaskStatusView().setProject(project);
				}

				@Override
				public void onFailure(Throwable caught) {
					Dialogs.alert("Erreur", "Erreur lors du chargement du projet : " + caught.getMessage(), null);
				}
			});
		} else {
			view.setProject(null);
		}
		panel.setWidget(view);
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

}
