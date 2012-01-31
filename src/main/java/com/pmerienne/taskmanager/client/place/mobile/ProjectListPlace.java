package com.pmerienne.taskmanager.client.place.mobile;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ProjectListPlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<ProjectListPlace> {

		@Override
		public ProjectListPlace getPlace(String token) {
			return new ProjectListPlace();
		}

		@Override
		public String getToken(ProjectListPlace place) {
			return "";
		}

	}
}
