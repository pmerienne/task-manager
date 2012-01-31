package com.pmerienne.taskmanager.client.widget.mobile;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;
import com.googlecode.mgwt.ui.client.widget.celllist.CellListWithHeader;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;
import com.googlecode.mgwt.ui.client.widget.celllist.HasCellSelectedHandler;
import com.pmerienne.taskmanager.client.widget.mobile.list.BasicCell;
import com.pmerienne.taskmanager.shared.model.Task;

public class TaskList extends CellListWithHeader<Task> implements HasCellSelectedHandler {

	private final static TaskCell TASK_CELL = new TaskCell();

	private List<Task> tasks = new ArrayList<Task>();

	public TaskList() {
		super(TASK_CELL);
		this.getCellList().setRound(true);
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
		this.getCellList().render(tasks);
	}

	public Task getTask(int index) {
		return this.tasks.get(index);
	}

	private static class TaskCell extends BasicCell<Task> {
		@Override
		public String getDisplayString(Task model) {
			return model.getName();
		}
	}

	@Override
	public HandlerRegistration addCellSelectedHandler(CellSelectedHandler cellSelectedHandler) {
		return this.getCellList().addCellSelectedHandler(cellSelectedHandler);
	}
}
