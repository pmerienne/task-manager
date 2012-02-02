package com.pmerienne.taskmanager.client.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class UserManagementViewImpl extends Composite {

	private static UserManagementViewImplUiBinder uiBinder = GWT.create(UserManagementViewImplUiBinder.class);

	interface UserManagementViewImplUiBinder extends UiBinder<Widget, UserManagementViewImpl> {
	}

	public UserManagementViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
