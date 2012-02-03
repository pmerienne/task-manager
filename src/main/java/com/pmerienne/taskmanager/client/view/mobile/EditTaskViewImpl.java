package com.pmerienne.taskmanager.client.view.mobile;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.MRadioButton;
import com.googlecode.mgwt.ui.client.widget.MTextArea;
import com.googlecode.mgwt.ui.client.widget.MTextBox;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.Task;
import com.pmerienne.taskmanager.shared.model.TaskStatus;
import com.pmerienne.taskmanager.shared.model.User;

public class EditTaskViewImpl extends Composite implements EditTaskView {

	private static EditTaskViewImplUiBinder uiBinder = GWT.create(EditTaskViewImplUiBinder.class);

	interface EditTaskViewImplUiBinder extends UiBinder<Widget, EditTaskViewImpl> {
	}

	@UiField
	HeaderButton cancel;

	@UiField
	MTextBox name;

	@UiField
	MTextArea description;

	@UiField
	ListBox projectList;

	@UiField
	ListBox userList;

	@UiField
	MRadioButton todo;

	@UiField
	MRadioButton doing;

	@UiField
	MRadioButton done;

	@UiField
	MRadioButton archived;

	private Task task;

	private Presenter presenter;

	private List<Project> availableProjects = new ArrayList<Project>();

	private List<User> availableUsers = new ArrayList<User>();

	public EditTaskViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		this.cancel.setVisible(!MGWT.getOsDetection().isAndroid());
	}

	@UiHandler("cancel")
	protected void onCancelTaped(TapEvent event) {
		this.presenter.cancel();
	}

	@UiHandler("save")
	protected void onSaveTaped(TapEvent event) {
		this.task.setName(this.name.getValue());
		this.task.setDescription(this.description.getValue());
		// Set status
		if (this.todo.getValue()) {
			this.task.setStatus(TaskStatus.TODO);
		} else if (this.doing.getValue()) {
			this.task.setStatus(TaskStatus.DOING);
		} else if (this.done.getValue()) {
			this.task.setStatus(TaskStatus.DONE);
		} else if (this.archived.getValue()) {
			this.task.setStatus(TaskStatus.ARCHIVED);
		}
		// Set project
		int selectedIndex = this.projectList.getSelectedIndex();
		Project selectedProject = this.availableProjects.get(selectedIndex);
		this.task.setProject(selectedProject);
		// Set users
		this.task.getUsers().clear();
		for (int i = 0; i < this.userList.getItemCount(); i++) {
			if (this.userList.isItemSelected(i)) {
				this.task.getUsers().add(this.availableUsers.get(i));
			}
		}
		this.presenter.save(this.task);
	}

	@Override
	public void setTask(Task task) {
		this.task = task;
		this.name.setValue(task.getName());
		this.description.setValue(task.getDescription());
		// Project
		int projectIndex = this.availableProjects.indexOf(task.getProject());
		if (projectIndex > 0) {
			this.projectList.setSelectedIndex(projectIndex);
		}
		// Status
		switch (task.getStatus()) {
		case TODO:
			this.todo.setValue(true);
			this.doing.setValue(false);
			this.done.setValue(false);
			this.archived.setValue(false);
			break;
		case DOING:
			this.todo.setValue(false);
			this.doing.setValue(true);
			this.done.setValue(false);
			this.archived.setValue(false);
			break;
		case DONE:
			this.todo.setValue(false);
			this.doing.setValue(false);
			this.done.setValue(true);
			this.archived.setValue(false);
			break;
		case ARCHIVED:
			this.todo.setValue(false);
			this.doing.setValue(false);
			this.done.setValue(false);
			this.archived.setValue(true);
			break;
		default:
			break;
		}
	}

	@Override
	public void setAvailableProjects(List<Project> projects) {
		this.availableProjects = projects;
		this.projectList.clear();
		for (Project project : projects) {
			this.projectList.addItem(project.getName());
		}
	}

	@Override
	public void setAvailableUsers(List<User> users) {
		this.availableUsers = users;
		this.userList.clear();
		for (User user : users) {
			this.userList.addItem(user.getLogin());
		}
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

}
