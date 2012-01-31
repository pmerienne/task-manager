package com.pmerienne.taskmanager.client.place.mobile;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ProjectDetailPlace extends Place {

	private String projectId;

	public ProjectDetailPlace(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectId() {
		return projectId;
	}

	public static class Tokenizer implements PlaceTokenizer<ProjectDetailPlace> {

		@Override
		public ProjectDetailPlace getPlace(String token) {
			return new ProjectDetailPlace(token);
		}

		@Override
		public String getToken(ProjectDetailPlace place) {
			return place.getProjectId();
		}

	}
}