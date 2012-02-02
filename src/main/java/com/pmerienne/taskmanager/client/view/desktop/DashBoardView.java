package com.pmerienne.taskmanager.client.view.desktop;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.Task;

public interface DashBoardView extends IsWidget {

	public interface Presenter {

		void goTo(Place place);

		void loadTask(Project project);

		void login(String login, String password);

		void logout();
	}

	void setPresenter(Presenter presenter);

	void setAvailableProjects(List<Project> projects);

	void setTasks(List<Task> tasks);

	void updateTask(Task task);

	void setLogged(boolean logged);
}