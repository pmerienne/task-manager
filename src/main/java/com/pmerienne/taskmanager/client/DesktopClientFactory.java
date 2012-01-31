package com.pmerienne.taskmanager.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.pmerienne.taskmanager.client.view.desktop.DashBoardView;

public interface DesktopClientFactory {

	EventBus getEventBus();

	PlaceController getPlaceController();

	DashBoardView getDashBoardView();
}
