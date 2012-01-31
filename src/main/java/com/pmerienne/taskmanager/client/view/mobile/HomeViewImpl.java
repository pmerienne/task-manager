package com.pmerienne.taskmanager.client.view.mobile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.widget.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.MTextBox;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.pmerienne.taskmanager.client.place.mobile.ProjectListPlace;
import com.pmerienne.taskmanager.client.place.mobile.TaskStatusPlace;
import com.pmerienne.taskmanager.client.widget.mobile.list.ViewList;
import com.pmerienne.taskmanager.client.widget.mobile.list.ViewList.Item;
import com.pmerienne.taskmanager.shared.model.User;

public class HomeViewImpl extends Composite implements HomeView {

	private static HomeViewImplUiBinder uiBinder = GWT.create(HomeViewImplUiBinder.class);

	interface HomeViewImplUiBinder extends UiBinder<Widget, HomeViewImpl> {
	}

	@UiField
	HeadingElement welcomeMessage;

	@UiField
	ViewList viewList;

	@UiField
	MTextBox login;

	@UiField
	MPasswordTextBox password;

	@UiField
	ScrollPanel userPanel;

	@UiField
	ScrollPanel loginPanel;

	private Presenter presenter;

	public HomeViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("viewList")
	protected void onViewSelected(CellSelectedEvent event) {
		Item item = this.viewList.getItem(event.getIndex());
		switch (item) {
		case PROJECTS:
			this.presenter.goTo(new ProjectListPlace());
			break;
		case TASKS:
			this.presenter.goTo(new TaskStatusPlace());
			break;
		case OPTIONS:

			break;
		default:
			break;
		}
	}

	@UiHandler("log")
	protected void onLogTaped(TapEvent event) {
		this.presenter.login(login.getValue(), password.getValue());
	}

	@Override
	public void setUser(User user) {
		if (user != null) {
			this.welcomeMessage.setInnerHTML("Bienvenue " + user.getLogin());
			this.loginPanel.setVisible(false);
			this.userPanel.setVisible(true);
		} else {
			this.loginPanel.setVisible(true);
			this.userPanel.setVisible(false);
		}
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
