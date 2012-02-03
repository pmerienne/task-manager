package com.pmerienne.taskmanager.client.view.mobile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.pmerienne.taskmanager.client.place.mobile.EditTaskPlace;
import com.pmerienne.taskmanager.client.place.mobile.TaskListPlace;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.TaskStatus;

public class TaskStatusViewImpl extends Composite implements TaskStatusView {

	private static TaskStatusViewImplUiBinder uiBinder = GWT.create(TaskStatusViewImplUiBinder.class);

	interface TaskStatusViewImplUiBinder extends UiBinder<Widget, TaskStatusViewImpl> {
	}

	@UiField
	HeaderButton back;

	@UiField
	HTML title;

	private Presenter presenter;

	private Project project;

	public TaskStatusViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		this.back.setVisible(!MGWT.getOsDetection().isAndroid());
	}

	@UiHandler("back")
	protected void onBackTaped(TapEvent event) {
		History.back();
	}

	@UiHandler("statusList")
	protected void onStatusSelected(CellSelectedEvent event) {
		int index = event.getIndex();
		String projectId = null;
		if(this.project != null) {
			projectId = this.project.getId();
		}
		switch (index) {
		case 0:
			this.presenter.goTo(new TaskListPlace(TaskStatus.TODO, projectId));
			break;
		case 1:
			this.presenter.goTo(new TaskListPlace(TaskStatus.DOING, projectId));
			break;
		case 2:
			this.presenter.goTo(new TaskListPlace(TaskStatus.DONE, projectId));
			break;
		case 3:
			this.presenter.goTo(new TaskListPlace(TaskStatus.ARCHIVED, projectId));
			break;
		default:
			break;
		}
	}

	@UiHandler("createNew")
	protected void onNewTaped(TapEvent event) {
		this.presenter.goTo(new EditTaskPlace());
	}

	@Override
	public void setProject(Project project) {
		this.project = project;
		if (project != null) {
			title.setText("Tâches (" + project.getName() + ")");
		} else {
			title.setText("Mes tâches");
		}

	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}
