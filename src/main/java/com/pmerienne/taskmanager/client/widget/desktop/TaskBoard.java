package com.pmerienne.taskmanager.client.widget.desktop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.allen_sauer.gwt.dnd.client.DragEndEvent;
import com.allen_sauer.gwt.dnd.client.DragHandler;
import com.allen_sauer.gwt.dnd.client.DragHandlerAdapter;
import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.drop.FlowPanelDropController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.pmerienne.taskmanager.client.resource.Bootstrap;
import com.pmerienne.taskmanager.client.resource.ResourceBundle;
import com.pmerienne.taskmanager.shared.model.Task;
import com.pmerienne.taskmanager.shared.model.TaskStatus;

public class TaskBoard extends Composite {

	private static TaskBoardUiBinder uiBinder = GWT.create(TaskBoardUiBinder.class);

	interface TaskBoardUiBinder extends UiBinder<Widget, TaskBoard> {
	}

	private final static Bootstrap bootstrap = ResourceBundle.INSTANCE.bootstrap();

	private Map<Task, HTML> tasks = new HashMap<Task, HTML>();
	private Map<TaskStatus, FlowPanelWithSpacer> columns = new HashMap<TaskStatus, FlowPanelWithSpacer>();

	@UiField
	FlowPanelWithSpacer todo;

	@UiField
	FlowPanelWithSpacer doing;

	@UiField
	FlowPanelWithSpacer done;

	@UiField
	FlowPanelWithSpacer archived;

	@UiField
	AbsolutePanel boundaryPanel;

	// private PickupDragController widgetDragController;

	public TaskBoard() {
		initWidget(uiBinder.createAndBindUi(this));
		this.columns.put(TaskStatus.TODO, todo);
		this.columns.put(TaskStatus.DOING, doing);
		this.columns.put(TaskStatus.DONE, done);
		this.columns.put(TaskStatus.ARCHIVED, archived);

		// Init drag
		// DragHandler dragHandler = new DragHandlerAdapter();
		// this.widgetDragController = new
		// PickupDragController(this.boundaryPanel, false);
		// this.widgetDragController.setBehaviorMultipleSelection(false);
		// this.widgetDragController.addDragHandler(dragHandler);
		// for (FlowPanelWithSpacer columnPanel : columns.values()) {
		// FlowPanelDropController widgetDropController = new
		// FlowPanelDropController(columnPanel);
		// widgetDragController.registerDropController(widgetDropController);
		// }
	}

	public void setTasks(List<Task> tasks) {
		this.clear();
		for (Task task : tasks) {
			this.addTask(task);
		}
	}

	public void addTask(Task task) {
		HTML widget = new HTML(task.getName());
		widget.addStyleName(bootstrap.alertMessage());
		widget.addStyleName(bootstrap.blockMessage());
		widget.addStyleName(bootstrap.info());
		this.columns.get(task.getStatus()).add(widget);
		// this.widgetDragController.makeDraggable(widget);
		this.tasks.put(task, widget);
	}

	public void removeTask(Task task) {
		if (this.tasks.containsKey(task)) {
			HTML widget = this.tasks.remove(task);
			this.columns.get(task.getStatus()).remove(widget);
			// this.widgetDragController.makeNotDraggable(widget);
		}
	}

	public void clear() {
		for (Task task : this.tasks.keySet()) {
			if (this.tasks.containsKey(task)) {
				HTML widget = this.tasks.get(task);
				this.columns.get(task.getStatus()).remove(widget);
				// this.widgetDragController.makeNotDraggable(widget);
			}
		}
		this.tasks.clear();
	}
}
