package com.pmerienne.taskmanager.client.widget;

import com.google.gwt.user.client.ui.TextBox;

public class ExtendedTextBox extends TextBox {

	private String placeholder = "";

	public ExtendedTextBox() {
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
