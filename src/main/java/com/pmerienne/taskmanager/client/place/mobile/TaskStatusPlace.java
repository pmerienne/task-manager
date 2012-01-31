package com.pmerienne.taskmanager.client.place.mobile;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.pmerienne.taskmanager.client.utils.StringUtils;

public class TaskStatusPlace extends Place {

	private String projectId;

	public TaskStatusPlace() {
	}

	public TaskStatusPlace(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectId() {
		return projectId;
	}

	public static class Tokenizer implements PlaceTokenizer<TaskStatusPlace> {

		@Override
		public TaskStatusPlace getPlace(String token) {
			if (StringUtils.isEmpty(token)) {
				return new TaskStatusPlace();
			} else {
				return new TaskStatusPlace(token);
			}
		}

		@Override
		public String getToken(TaskStatusPlace place) {
			if (StringUtils.isEmpty(place.getProjectId())) {
				return "";
			} else {
				return place.getProjectId();
			}
		}
	}
}
