package com.pmerienne.taskmanager.client.view.desktop;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.pmerienne.taskmanager.client.widget.desktop.ProjectList;
import com.pmerienne.taskmanager.client.widget.desktop.TaskBoard;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.Task;

public class DashBoardViewImpl extends Composite implements DashBoardView {

	private static DashBoardViewImplUiBinder uiBinder = GWT.create(DashBoardViewImplUiBinder.class);

	interface DashBoardViewImplUiBinder extends UiBinder<Widget, DashBoardViewImpl> {
	}

	@UiField
	HeadingElement projectName;

	@UiField
	Label projectCreationDate;

	@UiField
	Label projectDescription;

	@UiField
	ProjectList projectList;

	@UiField
	TaskBoard taskBoard;

	private SingleSelectionModel<Project> projectSelectionModel;

	private Presenter presenter;

	private Project project;

	public DashBoardViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		this.projectSelectionModel = new SingleSelectionModel<Project>();
		this.projectSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				Project project = projectSelectionModel.getSelectedObject();
				setProject(project);
			}
		});
		this.projectList.setSelectionModel(this.projectSelectionModel);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setAvailableProjects(List<Project> projects) {
		this.projectList.setRowData(projects);
		if (projects != null && !projects.isEmpty()) {
			this.projectSelectionModel.setSelected(projects.get(0), true);
		}
	}

	@Override
	public void setTasks(List<Task> tasks) {
		this.taskBoard.setTasks(tasks);
	}

	private void setProject(Project project) {
		// Set UI
		this.project = project;
		this.projectName.setInnerHTML("Projet : " + project.getName());
		StringBuilder sb = new StringBuilder();
		sb.append("Projet créé le ");
		sb.append(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT).format(project.getCreationDate()));
		this.projectCreationDate.setText(sb.toString());
		this.projectDescription.setText(project.getDescription());

		// Load tasks
		this.presenter.loadTask(project);
	}
}