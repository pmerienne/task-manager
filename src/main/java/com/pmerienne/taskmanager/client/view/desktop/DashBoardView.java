package com.pmerienne.taskmanager.client.view.desktop;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.Task;

public interface DashBoardView extends IsWidget {

	public interface Presenter {
		void loadTask(Project project);
	}

	void setPresenter(Presenter presenter);

	void setAvailableProjects(List<Project> projects);

	void setTasks(List<Task> tasks);

	void updateTask(Task task);
}