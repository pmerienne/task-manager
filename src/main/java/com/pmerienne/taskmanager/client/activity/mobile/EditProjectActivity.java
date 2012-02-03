package com.pmerienne.taskmanager.client.activity.mobile;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.pmerienne.taskmanager.client.MobileClientFactory;
import com.pmerienne.taskmanager.client.place.mobile.ProjectListPlace;
import com.pmerienne.taskmanager.client.utils.Services;
import com.pmerienne.taskmanager.client.view.mobile.EditProjectView;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.User;

public class EditProjectActivity extends MobileActivity implements EditProjectView.Presenter {

	private final String projectId;

	public EditProjectActivity(MobileClientFactory clientFactory) {
		super(clientFactory);
		this.projectId = null;
	}

	public EditProjectActivity(MobileClientFactory clientFactory, String projectId) {
		super(clientFactory);
		this.projectId = projectId;
	}
	
	@Override
	protected IsWidget getView() {
		return this.clientFactory.getEditProjectView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		EditProjectView view = this.clientFactory.getEditProjectView();
		view.setPresenter(this);
		loadAvailableUsers();
		loadProject();
	}

	private void loadAvailableUsers() {
		Services.userService.findAll(new AsyncCallback<List<User>>() {
			@Override
			public void onFailure(Throwable caught) {
				Dialogs.alert("Erreur", "Erreur lors du chargement des utilisateurs : " + caught.getMessage(), null);
			}

			@Override
			public void onSuccess(List<User> users) {
				clientFactory.getEditProjectView().setAvailableUsers(users);
			}
		});

	}

	private void loadProject() {
		if (this.projectId != null) {
			this.setPending(true);
			Services.projectService.findById(this.projectId, new AsyncCallback<Project>() {
				@Override
				public void onSuccess(Project project) {
					setPending(false);
					if (project != null) {
						clientFactory.getEditProjectView().setProject(project);
					} else {
						project = new Project();
						clientFactory.getEditProjectView().setProject(project);
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					setPending(false);
					Dialogs.alert("Erreur", "Erreur lors du chargement du projet : " + caught.getMessage(), null);
				}
			});
		} else {
			Project project = new Project();
			this.clientFactory.getEditProjectView().setProject(project);
		}
	}

	@Override
	public void save(Project project) {
		this.setPending(true);
		Services.projectService.save(project, new AsyncCallback<Project>() {
			@Override
			public void onFailure(Throwable caught) {
				setPending(false);
				Dialogs.alert("Erreur", "Erreur lors de la sauvegarde du projet : " + caught.getMessage(), null);
			}

			@Override
			public void onSuccess(Project project) {
				setPending(false);
				clientFactory.getPlaceController().goTo(new ProjectListPlace());
			}
		});
	}

	@Override
	public void delete(Project project) {
		this.setPending(true);
		Services.projectService.delete(project, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				setPending(false);
				Dialogs.alert("Erreur", "Erreur lors de la suppression du projet : " + caught.getMessage(), null);
			}

			@Override
			public void onSuccess(Void result) {
				setPending(false);
				Dialogs.alert("Information", "Le projet a été supprimé", null);
				clientFactory.getPlaceController().goTo(new ProjectListPlace());
			}
		});
	}

	@Override
	public void cancel() {
		History.back();
	}

}