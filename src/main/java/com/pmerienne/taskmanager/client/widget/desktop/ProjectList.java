package com.pmerienne.taskmanager.client.widget.desktop;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.pmerienne.taskmanager.shared.model.Project;

public class ProjectList extends CellList<Project> {

	public ProjectList() {
		super(new ProjectCell());
	}

	private static class ProjectCell extends AbstractCell<Project> {

		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context, Project project, SafeHtmlBuilder sb) {
			sb.appendHtmlConstant("<div style='font-weight: bold;padding: 5px'>");
			sb.appendEscaped(project.getName());
			sb.appendHtmlConstant("</div>");
		}
	}

}