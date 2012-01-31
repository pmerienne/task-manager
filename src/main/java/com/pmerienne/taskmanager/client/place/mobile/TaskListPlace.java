package com.pmerienne.taskmanager.client.place.mobile;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.pmerienne.taskmanager.shared.model.TaskStatus;

public class TaskListPlace extends Place {

	private TaskStatus status;
	private String projectId;

	public TaskListPlace(TaskStatus status, String projectId) {
		this.projectId = projectId;
		this.status = status;
	}

	public TaskListPlace(TaskStatus status) {
		this.status = status;
		this.projectId = null;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public String getProjectId() {
		return projectId;
	}

	public static class Tokenizer implements PlaceTokenizer<TaskListPlace> {

		private final static String PARAM_SEPARATOR = ":";
		private final static Integer PARAM_COUNT = 2;

		public TaskListPlace getPlace(String[] tokenParameters) {
			// Find status
			TaskStatus status;
			try {
				status = TaskStatus.valueOf(tokenParameters[0]);
			} catch (IllegalArgumentException iae) {
				status = TaskStatus.TODO;
			}
			return new TaskListPlace(status, tokenParameters[1]);
		}

		public String[] getTokenParameters(TaskListPlace place) {
			TaskStatus status = place.getStatus();
			if (status == null) {
				status = TaskStatus.TODO;
			}
			String projectId = place.getProjectId();
			if (projectId == null) {
				projectId = "";
			}
			return new String[] { status.name(), projectId };
		}

		public TaskListPlace getPlace(String token) {
			return this.getPlace(this.parseToken(token));
		}

		@Override
		public String getToken(TaskListPlace place) {
			return this.createToken(this.getTokenParameters(place));
		}

		private String[] parseToken(String token) {
			String[] parameters = new String[PARAM_COUNT];
			String[] actualParameters = token.split(PARAM_SEPARATOR);
			for (int i = 0; i < actualParameters.length && i < parameters.length; i++) {
				parameters[i] = actualParameters[i];
			}
			return parameters;
		}

		private String createToken(String[] parameters) {
			String token = "";
			for (String parameter : parameters) {
				token += parameter + PARAM_SEPARATOR;
			}
			return token;
		}
	}

}
