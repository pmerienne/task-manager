package com.pmerienne.taskmanager.client.activity.mobile;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.pmerienne.taskmanager.client.MobileClientFactory;
import com.pmerienne.taskmanager.client.utils.Services;
import com.pmerienne.taskmanager.client.view.mobile.ProjectDetailView;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.ProjectStatistics;

public class ProjectDetailActivity extends AbstractActivity implements ProjectDetailView.Presenter {

	private MobileClientFactory clientFactory;
	private String projectId;

	public ProjectDetailActivity(MobileClientFactory clientFactory, String projectId) {
		this.clientFactory = clientFactory;
		this.projectId = projectId;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		ProjectDetailView view = this.clientFactory.getProjectDetailView();
		view.setPresenter(this);
		panel.setWidget(view);
		loadProject(this.projectId);
		loadProjectStatistics(this.projectId);
	}

	@Override
	public void goTo(Place place) {
		this.clientFactory.getPlaceController().goTo(place);
	}

	private void loadProject(String projectId) {
		Services.projectService.findById(projectId, new AsyncCallback<Project>() {
			@Override
			public void onFailure(Throwable caught) {
				Dialogs.alert("Erreur", "Erreur lors du chargement du projet : " + caught.getMessage(), null);
			}

			@Override
			public void onSuccess(Project project) {
				if (project != null) {
					clientFactory.getProjectDetailView().setProject(project);
				}
			}
		});
	}

	private void loadProjectStatistics(String projectId) {
		Services.projectService.getProjectStatistics(projectId, new AsyncCallback<ProjectStatistics>() {
			@Override
			public void onFailure(Throwable caught) {
				Dialogs.alert("Erreur", "Erreur lors du chargement du projet : " + caught.getMessage(), null);
			}

			@Override
			public void onSuccess(ProjectStatistics stats) {
				clientFactory.getProjectDetailView().setProjectStatistics(stats);
			}
		});
	}
}
