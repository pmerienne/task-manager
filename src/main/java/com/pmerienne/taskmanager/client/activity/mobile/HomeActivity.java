package com.pmerienne.taskmanager.client.activity.mobile;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.pmerienne.taskmanager.client.MobileClientFactory;
import com.pmerienne.taskmanager.client.utils.Services;
import com.pmerienne.taskmanager.client.view.mobile.HomeView;
import com.pmerienne.taskmanager.shared.model.User;

public class HomeActivity extends MobileActivity implements HomeView.Presenter {

	public HomeActivity(MobileClientFactory clientFactory) {
		super(clientFactory);
	}

	@Override
	protected IsWidget getView() {
		return this.clientFactory.getHomeView();
	}

	@Override
	public void start(final AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		final HomeView view = this.clientFactory.getHomeView();
		view.setPresenter(this);
		Services.userService.getCurrentUser(new AsyncCallback<User>() {
			@Override
			public void onFailure(Throwable caught) {
				view.setUser(null);
			}

			@Override
			public void onSuccess(User user) {
				view.setUser(user);
			}
		});
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void login(String login, String password) {
		this.setPending(true);
		Services.userService.login(login, password, new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				setPending(false);
				clientFactory.getHomeView().setUser(user);
				if (user == null) {
					Dialogs.alert("Erreur", "Mauvais login/mot de passe", null);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				setPending(false);
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
