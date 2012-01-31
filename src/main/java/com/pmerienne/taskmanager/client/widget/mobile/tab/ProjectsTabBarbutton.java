package com.pmerienne.taskmanager.client.widget.mobile.tab;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.tabbar.BookmarkTabBarButtonCss;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabBarButtonBase;

public class ProjectsTabBarbutton extends TabBarButtonBase {

	public ProjectsTabBarbutton() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getBookmarkTabBarButtonCss());
	}

	public ProjectsTabBarbutton(BookmarkTabBarButtonCss css) {
		super(css);
		addStyleName(css.bookmark());
		setText("Projets");
	}

}
