package com.pmerienne.taskmanager.shared.messaging.event;

import com.google.gwt.event.shared.EventHandler;

public interface TaskSavedHandler extends EventHandler {

	void onTaskSaved(TaskSavedEvent event);
}