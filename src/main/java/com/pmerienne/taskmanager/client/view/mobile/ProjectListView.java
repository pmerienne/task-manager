package com.pmerienne.taskmanager.client.view.mobile;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.pmerienne.taskmanager.shared.model.Project;

public interface ProjectListView extends IsWidget {

	public interface Presenter {

		void goTo(Place place);
	}

	void setPresenter(Presenter presenter);

	void setProjects(List<Project> projects);
}
