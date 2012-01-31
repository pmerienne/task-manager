package com.pmerienne.taskmanager.client.view.mobile;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.pmerienne.taskmanager.client.place.mobile.EditProjectPlace;
import com.pmerienne.taskmanager.client.place.mobile.ProjectDetailPlace;
import com.pmerienne.taskmanager.client.widget.mobile.list.ProjectList;
import com.pmerienne.taskmanager.shared.model.Project;

public class ProjectListViewImpl extends Composite implements ProjectListView {

	private static ProjectListViewImplUiBinder uiBinder = GWT.create(ProjectListViewImplUiBinder.class);

	interface ProjectListViewImplUiBinder extends UiBinder<Widget, ProjectListViewImpl> {
	}

	private Presenter presenter;

	@UiField
	HeaderButton back;

	@UiField
	ProjectList projectList;

	public ProjectListViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		this.back.setVisible(!MGWT.getOsDetection().isAndroid());
	}

	@UiHandler("back")
	protected void onBackTaped(TapEvent event) {
		History.back();
	}

	@UiHandler("newProject")
	protected void onNewTaped(TapEvent event) {
		this.presenter.goTo(new EditProjectPlace());
	}

	@UiHandler("projectList")
	protected void onProjectSelected(CellSelectedEvent event) {
		Project project = this.projectList.getProject(event.getIndex());
		this.presenter.goTo(new ProjectDetailPlace(project.getId()));
	}

	@Override
	public void setProjects(List<Project> projects) {
		this.projectList.setProjects(projects);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}
