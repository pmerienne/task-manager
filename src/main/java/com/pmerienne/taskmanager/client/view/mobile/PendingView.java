package com.pmerienne.taskmanager.client.view.mobile;

import com.google.gwt.user.client.ui.IsWidget;

public interface PendingView extends IsWidget {

	void setMessage(String message);

	void clear();
}
