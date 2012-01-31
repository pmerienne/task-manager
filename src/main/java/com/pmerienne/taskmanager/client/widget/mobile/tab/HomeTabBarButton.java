package com.pmerienne.taskmanager.client.widget.mobile.tab;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.tabbar.ContactsTabBarButtonCss;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabBarButtonBase;

public class HomeTabBarButton extends TabBarButtonBase {

	public HomeTabBarButton() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getContactsTabBarButtonCss());
	}

	public HomeTabBarButton(ContactsTabBarButtonCss css) {
		super(css);
		addStyleName(css.contacts());
		setText("Home");
	}

}