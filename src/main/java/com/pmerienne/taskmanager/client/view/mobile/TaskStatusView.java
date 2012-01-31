package com.pmerienne.taskmanager.client.view.mobile;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.pmerienne.taskmanager.shared.model.Project;

public interface TaskStatusView extends IsWidget {

	public interface Presenter {

		void goTo(Place place);
	}

	void setPresenter(Presenter presenter);

	void setProject(Project project);
}
