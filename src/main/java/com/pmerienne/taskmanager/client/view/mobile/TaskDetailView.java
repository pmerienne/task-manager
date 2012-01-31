package com.pmerienne.taskmanager.client.view.mobile;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.pmerienne.taskmanager.shared.model.Task;

public interface TaskDetailView extends IsWidget {

	public interface Presenter {

		void goTo(Place place);

		void save(Task task);

		void delete(Task task);
	}

	void setPresenter(Presenter presenter);

	void setTask(Task task);
}
