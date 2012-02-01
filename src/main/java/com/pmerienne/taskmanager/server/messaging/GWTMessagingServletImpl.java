package com.pmerienne.taskmanager.server.messaging;

import javax.servlet.ServletException;

import com.pmerienne.taskmanager.server.service.ApplicationContextHolder;
import com.pmerienne.taskmanager.shared.messaging.DomainName;
import com.pmerienne.taskmanager.shared.messaging.event.TaskSavedEvent;
import com.pmerienne.taskmanager.shared.model.Task;

import de.novanic.eventservice.client.event.domain.Domain;
import de.novanic.eventservice.client.event.domain.DomainFactory;
import de.novanic.eventservice.service.EventServiceImpl;

public class GWTMessagingServletImpl extends EventServiceImpl implements MessageHandler {

	private static final long serialVersionUID = 4925922732085721124L;

	private final static Domain TASK_DOMAIN = DomainFactory.getDomain(DomainName.TASK.getName());

	@Override
	public void onTaskSaved(Task task) {
		this.addEvent(TASK_DOMAIN, new TaskSavedEvent(task));
	}

	@Override
	public void init() throws ServletException {
		super.init();
		MessagingService messagingService = ApplicationContextHolder.getContext().getBean(MessagingService.class);
		messagingService.addHandler(this);
	}
}
