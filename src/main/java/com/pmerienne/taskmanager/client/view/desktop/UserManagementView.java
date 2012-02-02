package com.pmerienne.taskmanager.client.view.desktop;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.Range;
import com.pmerienne.taskmanager.shared.model.User;

public interface UserManagementView extends IsWidget {

	public interface Presenter {

		void goTo(Place place);

		void loadUser(Range range);

		void save(User user);

		void delete(User user);

		void logout();
	}

	void setPresenter(Presenter presenter);

	void setUsers(List<User> user);
}