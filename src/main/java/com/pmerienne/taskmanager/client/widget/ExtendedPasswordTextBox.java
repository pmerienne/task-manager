package com.pmerienne.taskmanager.client.widget;

import com.google.gwt.user.client.ui.PasswordTextBox;

public class ExtendedPasswordTextBox extends PasswordTextBox {

	private String placeholder = "";

	public ExtendedPasswordTextBox() {
		super();
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = (placeholder != null ? placeholder : "");
		getElement().setPropertyString("placeholder", this.placeholder);

	}

}
