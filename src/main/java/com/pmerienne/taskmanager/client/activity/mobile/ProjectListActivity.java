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
import com.pmerienne.taskmanager.client.view.mobile.ProjectListView;
import com.pmerienne.taskmanager.shared.model.Project;

public class ProjectListActivity extends AbstractActivity implements ProjectListView.Presenter {
	private final MobileClientFactory clientFactory;

	public ProjectListActivity(MobileClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		ProjectListView view = this.clientFactory.getProjectListView();
		view.setPresenter(this);
		panel.setWidget(view);
		loadProjects();
	}

	private void loadProjects() {
		Services.projectService.findAll(new AsyncCallback<List<Project>>() {
			@Override
			public void onSuccess(List<Project> projects) {
				clientFactory.getProjectListView().setProjects(projects);
			}

			@Override
			public void onFailure(Throwable caught) {
				Dialogs.alert("Erreur", "Erreur lors du chargement des projets : " + caught.getMessage(), null);
			}
		});
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

}
