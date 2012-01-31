package com.pmerienne.taskmanager.server.messaging;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pmerienne.taskmanager.shared.model.Task;

@Service("messagingService")
public class MessagingServiceImpl implements MessagingService {

	private List<MessageHandler> handlers = new ArrayList<MessageHandler>();

	public void onTaskSaved(Task task) {
		for (MessageHandler handler : this.handlers) {
			handler.onTaskSaved(task);
		}
	}

	@Override
	public void addHandler(MessageHandler handler) {
		this.handlers.add(handler);
	}

	@Override
	public void removeHandler(MessageHandler handler) {
		this.handlers.remove(handler);
	}

}
