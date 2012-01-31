package com.pmerienne.taskmanager.shared.messaging.event;

import com.google.gwt.event.shared.GwtEvent;
import com.pmerienne.taskmanager.shared.model.Task;

import de.novanic.eventservice.client.event.Event;

public class TaskSavedEvent extends GwtEvent<TaskSavedHandler> implements Event {

	private static final long serialVersionUID = -6017567341228759706L;

	private static Type<TaskSavedHandler> TYPE;

	public static Type<TaskSavedHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<TaskSavedHandler>());
	}

	@Override
	protected void dispatch(TaskSavedHandler handler) {
		handler.onTaskSaved(this);
	}

	@Override
	public GwtEvent.Type<TaskSavedHandler> getAssociatedType() {
		return getType();
	}

	private Task task;

	public TaskSavedEvent(Task task) {
		this.task = task;
	}

	public Task getTask() {
		return task;
	}

}
