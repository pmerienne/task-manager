package com.pmerienne.taskmanager.client.view.mobile;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.Task;

public interface EditTaskView extends IsWidget {

	public interface Presenter {

		void save(Task task);

		void cancel();

	}

	void setPresenter(Presenter presenter);

	void setTask(Task task);
	
	void setAvailableProjects(List<Project> projects);
}