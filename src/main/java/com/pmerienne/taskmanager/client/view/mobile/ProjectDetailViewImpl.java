package com.pmerienne.taskmanager.client.view.mobile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.pmerienne.taskmanager.client.place.mobile.EditProjectPlace;
import com.pmerienne.taskmanager.client.place.mobile.TaskStatusPlace;
import com.pmerienne.taskmanager.client.widget.mobile.chart.ProjectTaskChart;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.ProjectStatistics;
import com.pmerienne.taskmanager.shared.model.User;

public class ProjectDetailViewImpl extends Composite implements ProjectDetailView {

	private static ProjectDetailViewImplUiBinder uiBinder = GWT.create(ProjectDetailViewImplUiBinder.class);

	interface ProjectDetailViewImplUiBinder extends UiBinder<Widget, ProjectDetailViewImpl> {
	}

	@UiField
	HeaderButton back;

	@UiField
	HTML title;

	@UiField
	Label informations;

	@UiField
	Label users;

	@UiField
	Label description;

	@UiField
	ProjectTaskChart chart;

	private Project project;

	private Presenter presenter;

	public ProjectDetailViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		this.back.setVisible(!MGWT.getOsDetection().isAndroid());
	}

	@UiHandler("back")
	protected void onBackTaped(TapEvent event) {
		History.back();
	}

	@UiHandler("edit")
	protected void onEditTaped(TapEvent event) {
		this.presenter.goTo(new EditProjectPlace(this.project.getId()));
	}

	@UiHandler("viewTasks")
	protected void onViewTasksTapped(TapEvent event) {
		this.presenter.goTo(new TaskStatusPlace(this.project.getId()));
	}

	@Override
	public void setProject(Project project) {
		this.project = project;
		this.title.setText(project.getName());
		this.informations.setText(this.getInformation(project));
		this.description.setText(project.getDescription());
		this.users.setText(this.getUsers(project));
	}

	private String getInformation(Project project) {
		StringBuilder sb = new StringBuilder();
		sb.append("Projet créé le ");
		sb.append(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT).format(project.getCreationDate()));
		return sb.toString();

	}

	private String getUsers(Project project) {
		StringBuilder sb = new StringBuilder();
		sb.append("Utilisateurs : ");
		for (User user : project.getUsers()) {
			sb.append(user.getLogin() + ", ");
		}
		if (!project.getUsers().isEmpty()) {
			sb.delete(sb.length() - 2, sb.length() - 1);
		}
		return sb.toString();

	}

	@Override
	public void setProjectStatistics(final ProjectStatistics stats) {
		this.chart.setProjectStatistics(stats);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
