package com.pmerienne.taskmanager.client.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface ResourceBundle extends ClientBundle {

	public static ResourceBundle INSTANCE = GWT.create(ResourceBundle.class);

	Style style();

	@Source("bootstrap.css")
	Bootstrap bootstrap();

}
