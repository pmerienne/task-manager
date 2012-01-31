package com.pmerienne.taskmanager.client.place.mobile;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class TaskDetailPlace extends Place {

	private String taskId;

	public TaskDetailPlace(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskId() {
		return taskId;
	}

	public static class Tokenizer implements PlaceTokenizer<TaskDetailPlace> {

		@Override
		public TaskDetailPlace getPlace(String token) {
			return new TaskDetailPlace(token);
		}

		@Override
		public String getToken(TaskDetailPlace place) {
			return place.getTaskId();
		}

	}
}
