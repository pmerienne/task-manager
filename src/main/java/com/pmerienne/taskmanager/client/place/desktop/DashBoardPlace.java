package com.pmerienne.taskmanager.client.place.desktop;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DashBoardPlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<DashBoardPlace> {

		@Override
		public DashBoardPlace getPlace(String token) {
			return new DashBoardPlace();
		}

		@Override
		public String getToken(DashBoardPlace place) {
			return "";
		}

	}
}
