package com.pmerienne.taskmanager.server.messaging;

import com.pmerienne.taskmanager.shared.model.Task;

public interface MessageHandler {

	void onTaskSaved(Task task);
}
