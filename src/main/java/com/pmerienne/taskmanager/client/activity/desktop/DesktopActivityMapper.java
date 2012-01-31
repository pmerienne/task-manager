package com.pmerienne.taskmanager.client.activity.desktop;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.pmerienne.taskmanager.client.DesktopClientFactory;
import com.pmerienne.taskmanager.client.place.desktop.DashBoardPlace;

public class DesktopActivityMapper implements ActivityMapper {

	private final DesktopClientFactory clientFactory;

	public DesktopActivityMapper(DesktopClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof DashBoardPlace) {
			return new DashBoardActivity(clientFactory);
		}

		// default
		return new DashBoardActivity(clientFactory);
	}

}
