package com.pmerienne.taskmanager.client.activity.mobile;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.pmerienne.taskmanager.client.MobileClientFactory;

public abstract class MobileActivity extends AbstractActivity {

	private AcceptsOneWidget panel;

	protected MobileClientFactory clientFactory;

	public MobileActivity(MobileClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.panel = panel;
		IsWidget view = this.getView();
		panel.setWidget(view);
	}

	protected void setPending(boolean pending) {
		this.setPending(pending, null);
	}

	protected void setPending(boolean pending, String message) {
		if(message != null) {
			this.clientFactory.getPendingView().setMessage(message);
		} else {
			this.clientFactory.getPendingView().clear();
		}
		if(pending) {
			this.panel.setWidget(this.clientFactory.getPendingView());
		} else {
			this.panel.setWidget(this.getView());
		}
	}

	protected abstract IsWidget getView();

}
