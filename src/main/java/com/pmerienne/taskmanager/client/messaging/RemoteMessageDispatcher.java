package com.pmerienne.taskmanager.client.messaging;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.pmerienne.taskmanager.shared.messaging.DomainName;

import de.novanic.eventservice.client.event.Event;
import de.novanic.eventservice.client.event.RemoteEventService;
import de.novanic.eventservice.client.event.RemoteEventServiceFactory;
import de.novanic.eventservice.client.event.domain.Domain;
import de.novanic.eventservice.client.event.domain.DomainFactory;
import de.novanic.eventservice.client.event.listener.RemoteEventListener;

public class RemoteMessageDispatcher implements RemoteEventListener {

	private EventBus eventBus;

	private final static Domain TASK_DOMAIN = DomainFactory.getDomain(DomainName.TASK.getName());

	private static RemoteMessageDispatcher INSTANCE;

	public static void start(EventBus eventBus) {
		if (INSTANCE == null) {
			INSTANCE = new RemoteMessageDispatcher(eventBus);
		}
	}

	public RemoteMessageDispatcher(EventBus eventBus) {
		this.eventBus = eventBus;
		RemoteEventService remoteEventService = RemoteEventServiceFactory.getInstance().getRemoteEventService();
		// add a listener to the SERVER_MESSAGE_DOMAIN
		remoteEventService.addListener(TASK_DOMAIN, this);
	}

	@Override
	public void apply(Event event) {
		if (event instanceof GwtEvent<?>) {
			this.eventBus.fireEvent((GwtEvent<?>) event);
		}
	}

}
