package com.pmerienne.taskmanager.client.widget.mobile;

import java.util.Arrays;

import com.google.gwt.event.shared.HandlerRegistration;
import com.googlecode.mgwt.ui.client.widget.celllist.CellListWithHeader;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;
import com.googlecode.mgwt.ui.client.widget.celllist.HasCellSelectedHandler;
import com.pmerienne.taskmanager.client.widget.mobile.list.BasicCell;
import com.pmerienne.taskmanager.shared.model.TaskStatus;

public class TaskStatusList extends CellListWithHeader<TaskStatus> implements HasCellSelectedHandler {

	private final static TaskStatusCell TASK_STATUS_CELL = new TaskStatusCell();

	public TaskStatusList() {
		super(TASK_STATUS_CELL);
		this.getCellList().setRound(true);
		this.getCellList().render(Arrays.asList(TaskStatus.values()));
	}

	private static class TaskStatusCell extends BasicCell<TaskStatus> {

		@Override
		public String getDisplayString(TaskStatus model) {
			switch (model) {
			case TODO:
				return "Tâches à faire";
			case DOING:
				return "Tâches en cours";
			case DONE:
				return "Tâches finies";
			case ARCHIVED:
				return "Tâches archivées";
			default:
				break;
			}
			return null;
		}

	}

	@Override
	public HandlerRegistration addCellSelectedHandler(CellSelectedHandler cellSelectedHandler) {
		return this.getCellList().addCellSelectedHandler(cellSelectedHandler);
	}
}
