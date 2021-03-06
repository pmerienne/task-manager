package com.pmerienne.taskmanager.client.activity.mobile;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.pmerienne.taskmanager.client.MobileClientFactory;
import com.pmerienne.taskmanager.client.utils.Services;
import com.pmerienne.taskmanager.client.utils.StringUtils;
import com.pmerienne.taskmanager.client.view.mobile.TaskStatusView;
import com.pmerienne.taskmanager.shared.model.Project;

public class TaskStatusActivity extends MobileActivity implements TaskStatusView.Presenter {

	private String projectId;

	public TaskStatusActivity(MobileClientFactory clientFactory) {
		super(clientFactory);
		this.clientFactory = clientFactory;
	}

	public TaskStatusActivity(MobileClientFactory clientFactory, String projectId) {
		super(clientFactory);
		this.projectId = projectId;
	}
	
	@Override
	protected IsWidget getView() {
		return this.clientFactory.getTaskStatusView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		TaskStatusView view = this.clientFactory.getTaskStatusView();
		view.setPresenter(this);
		if (!StringUtils.isEmpty(this.projectId)) {
			this.setPending(true);
			Services.projectService.findById(this.projectId, new AsyncCallback<Project>() {
				@Override
				public void onSuccess(Project project) {
					setPending(false);
					clientFactory.getTaskStatusView().setProject(project);
				}

				@Override
				public void onFailure(Throwable caught) {
					setPending(false);
					Dialogs.alert("Erreur", "Erreur lors du chargement du projet : " + caught.getMessage(), null);
				}
			});
		} else {
			view.setProject(null);
		}
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

}
