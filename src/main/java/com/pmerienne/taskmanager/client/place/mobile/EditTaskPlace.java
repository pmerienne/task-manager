package com.pmerienne.taskmanager.client.place.mobile;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.pmerienne.taskmanager.shared.model.TaskStatus;

public class EditTaskPlace extends Place {

	private String taskId = "";
	private String projectId = "";
	private TaskStatus status;

	public EditTaskPlace() {
		this.status = TaskStatus.TODO;
	}

	public EditTaskPlace(TaskStatus status) {
		super();
		this.status = status;
	}

	public EditTaskPlace(TaskStatus status, String projectId) {
		super();
		this.projectId = projectId;
		this.status = status;
	}

	public EditTaskPlace(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskId() {
		return taskId;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public String getProjectId() {
		return projectId;
	}

	public static class Tokenizer implements PlaceTokenizer<EditTaskPlace> {

		private final static String PARAM_SEPARATOR = ":";

		@Override
		public EditTaskPlace getPlace(String token) {
			EditTaskPlace place = null;
			String[] params = this.parseToken(token);
			if (params.length < 2) {
				// taskId
				place = new EditTaskPlace(token);
			} else {
				TaskStatus status;
				try {
					status = TaskStatus.valueOf(params[0]);
				} catch (IllegalArgumentException iae) {
					status = TaskStatus.TODO;
				}
				place = new EditTaskPlace(status, params[1]);
			}
			return place;
		}

		@Override
		public String getToken(EditTaskPlace place) {
			// We got a task id
			if (place.getTaskId() != null && !place.getTaskId().isEmpty()) {
				return place.getTaskId();
			}
			// We got some information for a new task
			return place.getStatus().name() + PARAM_SEPARATOR + place.getProjectId();
		}

		private String[] parseToken(String token) {
			return token.trim().split(PARAM_SEPARATOR);
		}
	}
}