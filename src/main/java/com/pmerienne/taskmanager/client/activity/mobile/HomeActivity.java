package com.pmerienne.taskmanager.client.activity.mobile;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.pmerienne.taskmanager.client.MobileClientFactory;
import com.pmerienne.taskmanager.client.utils.Services;
import com.pmerienne.taskmanager.client.view.mobile.HomeView;
import com.pmerienne.taskmanager.shared.model.User;

public class HomeActivity extends AbstractActivity implements HomeView.Presenter {
	private final MobileClientFactory clientFactory;

	public HomeActivity(MobileClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		final HomeView view = clientFactory.getHomeView();
		view.setPresenter(this);
		Services.userService.getCurrentUser(new AsyncCallback<User>() {
			@Override
			public void onFailure(Throwable caught) {
				view.setUser(null);
				panel.setWidget(view);
			}

			@Override
			public void onSuccess(User user) {
				view.setUser(user);
				panel.setWidget(view);
			}
		});
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void login(String login, String password) {
		Services.userService.login(login, password, new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				clientFactory.getHomeView().setUser(user);
				if(user == null) {
					Dialogs.alert("Erreur", "Mauvais login/mot de passe", null);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Dialogs.alert("Erreur", "Erreur lors de la connection : " + caught.getMessage(), null);
			}
		});
	}

	@Override
	public void logout() {
		Services.userService.logout(new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Dialogs.alert("Erreur", "Erreur lors de la déconnection : " + caught.getMessage(), null);
			}

			@Override
			public void onSuccess(Void result) {
				clientFactory.getHomeView().setUser(null);
			}
		});
	}

}
