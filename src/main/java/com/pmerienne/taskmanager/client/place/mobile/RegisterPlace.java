package com.pmerienne.taskmanager.client.place.mobile;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class RegisterPlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<RegisterPlace> {

		@Override
		public RegisterPlace getPlace(String token) {
			return new RegisterPlace();
		}

		@Override
		public String getToken(RegisterPlace place) {
			return "";
		}

	}
}
