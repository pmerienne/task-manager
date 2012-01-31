package com.pmerienne.taskmanager.client.widget.mobile.list;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;
import com.googlecode.mgwt.ui.client.widget.celllist.CellListWithHeader;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;
import com.googlecode.mgwt.ui.client.widget.celllist.HasCellSelectedHandler;
import com.pmerienne.taskmanager.shared.model.Project;

public class ProjectList extends CellListWithHeader<Project> implements HasCellSelectedHandler {

	private final static ProjectCell PROJECT_CELL = new ProjectCell();

	private List<Project> projects = new ArrayList<Project>();

	public ProjectList() {
		super(PROJECT_CELL);
		this.getCellList().setRound(true);
	}

	public List<Project> getTasks() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
		this.getCellList().render(projects);
	}

	public Project getProject(int index) {
		return this.projects.get(index);
	}

	private static class ProjectCell extends BasicCell<Project> {
		@Override
		public String getDisplayString(Project model) {
			return model.getName();
		}
	}

	@Override
	public HandlerRegistration addCellSelectedHandler(CellSelectedHandler cellSelectedHandler) {
		return this.getCellList().addCellSelectedHandler(cellSelectedHandler);
	}
}