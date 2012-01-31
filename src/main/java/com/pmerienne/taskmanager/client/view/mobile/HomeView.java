package com.pmerienne.taskmanager.client.view.mobile;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.pmerienne.taskmanager.shared.model.User;

public interface HomeView extends IsWidget {

	public interface Presenter {

		void goTo(Place place);

		void login(String login, String password);

		void logout();
	}

	void setPresenter(Presenter presenter);

	void setUser(User user);
}
