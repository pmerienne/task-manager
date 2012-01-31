package com.pmerienne.taskmanager.shared.messaging;

public enum DomainName {

	TASK("TASK");

	private String name;

	DomainName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
