package com.pmerienne.taskmanager.client.theme;

import com.google.gwt.core.client.GWT;
import com.googlecode.mgwt.ui.client.theme.MGWTClientBundle;
import com.googlecode.mgwt.ui.client.theme.MGWTTheme;

public class TaskManagerTheme implements MGWTTheme {

	private final static TaskManagerTheme INSTANCE = new TaskManagerTheme();

	private MGWTClientBundle clientBundle;

	public static TaskManagerTheme get() {
		return INSTANCE;
	}

	@Override
	public MGWTClientBundle getMGWTClientBundle() {
		if (this.clientBundle == null) {
			this.clientBundle = GWT.create(TaskManagerThemeBundleRetina.class);
		}
		return this.clientBundle;
	}

}
