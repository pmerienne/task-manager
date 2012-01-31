package com.pmerienne.taskmanager.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.pmerienne.taskmanager.client.view.desktop.DashBoardView;
import com.pmerienne.taskmanager.client.view.desktop.DashBoardViewImpl;

public class DesktopClientFactoryImpl implements DesktopClientFactory {

	private final EventBus eventBus = new SimpleEventBus();
	@SuppressWarnings("deprecation")
	private final PlaceController placeController = new PlaceController(eventBus);

	private final DashBoardView dashBoardView = new DashBoardViewImpl();

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public DashBoardView getDashBoardView() {
		return dashBoardView;
	}

}
