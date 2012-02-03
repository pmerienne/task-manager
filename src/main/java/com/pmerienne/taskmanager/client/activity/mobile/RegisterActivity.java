package com.pmerienne.taskmanager.client.activity.mobile;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.pmerienne.taskmanager.client.MobileClientFactory;
import com.pmerienne.taskmanager.client.place.mobile.HomePlace;
import com.pmerienne.taskmanager.client.utils.Services;
import com.pmerienne.taskmanager.client.view.mobile.RegisterView;
import com.pmerienne.taskmanager.shared.model.User;

public class RegisterActivity extends AbstractActivity implements RegisterView.Presenter {

	private MobileClientFactory clientFactory;
	
	public RegisterActivity(MobileClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		RegisterView view = this.clientFactory.getRegisterView();
		view.setPresenter(this);
		view.clear();
		panel.setWidget(view);
	}

	@Override
	public void goTo(Place place) {
		this.clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void save(User user) {
		this.clientFactory.getRegisterView().setPending(true);
		Services.userService.save(user, new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				clientFactory.getPlaceController().goTo(new HomePlace());
				clientFactory.getRegisterView().clear();
			}

			@Override
			public void onFailure(Throwable caught) {
				Dialogs.alert("Erreur", "Erreur lors de l'inscription : " + caught.getMessage(), null);
				clientFactory.getRegisterView().clear();
			}
		});
	}

}
