package com.pmerienne.taskmanager.client.place.mobile;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EditProjectPlace extends Place {

	private String projectId = "";

	public EditProjectPlace() {

	}

	public EditProjectPlace(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectId() {
		return projectId;
	}

	public static class Tokenizer implements PlaceTokenizer<EditProjectPlace> {
		@Override
		public EditProjectPlace getPlace(String token) {
			return new EditProjectPlace(token);
		}

		@Override
		public String getToken(EditProjectPlace place) {
			return place.getProjectId();
		}
	}
}