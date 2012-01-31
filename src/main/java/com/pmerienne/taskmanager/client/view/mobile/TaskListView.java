package com.pmerienne.taskmanager.client.view.mobile;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.pmerienne.taskmanager.shared.model.Project;
import com.pmerienne.taskmanager.shared.model.Task;
import com.pmerienne.taskmanager.shared.model.TaskStatus;

public interface TaskListView extends IsWidget {

	public interface Presenter {

		void goTo(Place place);
	}

	void setPresenter(Presenter presenter);

	void setTasks(List<Task> tasks);

	void setTaskStatus(TaskStatus status);
	
	void setProject(Project project);
}
