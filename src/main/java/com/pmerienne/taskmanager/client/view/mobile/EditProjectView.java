package com.pmerienne.taskmanager.client.view.mobile;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.User;

public interface EditProjectView extends IsWidget {

	public interface Presenter {

		void save(Project project);

		void cancel();

		void delete(Project project);

	}

	void setPresenter(Presenter presenter);

	void setProject(Project project);

	void setAvailableUsers(List<User> users);
}