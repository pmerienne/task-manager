package com.pmerienne.taskmanager.client.view.mobile;

import java.util.List;

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
import com.pmerienne.taskmanager.client.place.mobile.TaskDetailPlace;
import com.pmerienne.taskmanager.client.widget.mobile.TaskList;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.Task;
import com.pmerienne.taskmanager.shared.model.TaskStatus;

public class TaskListViewImpl extends Composite implements TaskListView {

	private static TaskListViewImplUiBinder uiBinder = GWT.create(TaskListViewImplUiBinder.class);

	interface TaskListViewImplUiBinder extends UiBinder<Widget, TaskListViewImpl> {
	}

	@UiField
	HeaderButton back;

	@UiField
	HTML title;

	@UiField
	TaskList taskList;

	private TaskStatus status;

	private Presenter presenter;

	public TaskListViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		this.back.setVisible(!MGWT.getOsDetection().isAndroid());
	}

	@UiHandler("back")
	protected void onBackTaped(TapEvent event) {
		History.back();
	}

	@UiHandler("newTask")
	protected void onNewTaped(TapEvent event) {
		this.presenter.goTo(new EditTaskPlace(this.status));
	}

	@UiHandler("taskList")
	protected void onTaskSelected(CellSelectedEvent event) {
		Task task = this.taskList.getTask(event.getIndex());
		this.presenter.goTo(new TaskDetailPlace(task.getId()));
	}

	@Override
	public void setTaskStatus(TaskStatus status) {
		this.status = status;
		String title = "";
		switch (status) {
		case TODO:
			title = "Tâches à faire";
			break;
		case DOING:
			title = "Tâches en cours";
			break;
		case DONE:
			title = "Tâches finies";
			break;
		case ARCHIVED:
			title = "Tâches archivées";
			break;
		default:
			break;
		}
		this.title.setText(title);
	}

	@Override
	public void setTasks(List<Task> tasks) {
		this.taskList.setTasks(tasks);
	}

	@Override
	public void setProject(Project project) {
//		String title = project.getName();
//		switch (this.status) {
//		case TODO:
//			title += " : à faire";
//			break;
//		case DOING:
//			title += " : en cours";
//			break;
//		case DONE:
//			title += " : finies";
//			break;
//		case ARCHIVED:
//			title += " : archivées";
//			break;
//		default:
//			break;
//		}
//		this.title.setText(title);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}
