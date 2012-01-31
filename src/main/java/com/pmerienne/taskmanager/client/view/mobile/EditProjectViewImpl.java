package com.pmerienne.taskmanager.client.view.mobile;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.MTextArea;
import com.googlecode.mgwt.ui.client.widget.MTextBox;
import com.pmerienne.taskmanager.client.utils.StringUtils;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.User;

public class EditProjectViewImpl extends Composite implements EditProjectView {

	private static EditProjectViewImplUiBinder uiBinder = GWT.create(EditProjectViewImplUiBinder.class);

	interface EditProjectViewImplUiBinder extends UiBinder<Widget, EditProjectViewImpl> {
	}

	@UiField
	ListBox userList;

	@UiField
	MTextBox name;

	@UiField
	MTextArea description;

	@UiField
	Button delete;

	private Presenter presenter;

	private List<User> availableUsers = new ArrayList<User>();

	private Project project;

	public EditProjectViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("cancel")
	protected void onBackTaped(TapEvent event) {
		History.back();
	}

	@UiHandler("userList")
	protected void onUsersChanged(ChangeEvent event) {
		this.project.getUsers().clear();
		for (int i = 0; i < this.userList.getVisibleItemCount(); i++) {
			if (this.userList.isItemSelected(i)) {
				this.project.getUsers().add(this.availableUsers.get(i));
			}
		}
	}

	@UiHandler("save")
	protected void onSaveTaped(TapEvent event) {
		this.project.setName(this.name.getValue());
		this.project.setDescription(this.description.getValue());
		this.presenter.save(this.project);
	}

	@UiHandler("delete")
	protected void onDeleteTapped(TapEvent event) {
		this.presenter.delete(this.project);
	}

	@Override
	public void setProject(Project project) {
		this.delete.setVisible(!StringUtils.isEmpty(project.getId()));
		this.project = project;
		this.name.setValue(project.getName());
		this.description.setValue(project.getDescription());
		for (User user : project.getUsers()) {
			int index = this.availableUsers.indexOf(user);
			if (index > 0) {
				this.userList.setSelectedIndex(index);
			}
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
