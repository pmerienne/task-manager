package com.pmerienne.taskmanager.client.widget.mobile.tab;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.tabbar.HistoryTabBarButtonCss;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabBarButtonBase;

public class TasksTabBarButton extends TabBarButtonBase {

	public TasksTabBarButton() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getHistoryTabBarButtonCss());
	}

	public TasksTabBarButton(HistoryTabBarButtonCss css) {
		super(css);
		addStyleName(css.history());
		setText("Tâches");
	}

}
