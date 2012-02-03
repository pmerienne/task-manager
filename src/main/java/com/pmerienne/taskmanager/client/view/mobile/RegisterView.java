package com.pmerienne.taskmanager.client.view.mobile;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.pmerienne.taskmanager.shared.model.User;

public interface RegisterView extends IsWidget {

	public interface Presenter {

		void goTo(Place place);

		void save(User user);
	}

	void setPresenter(Presenter presenter);
	
	void clear();

}
