package com.pmerienne.taskmanager.client.place.mobile;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public abstract class ParamTokenizer<P extends Place> implements PlaceTokenizer<P> {

	private final static String PARAM_SEPARATOR = ":";

	public abstract P getPlace(String[] tokenParameters);

	public abstract String[] getTokenParameters(P place);

	public P getPlace(String token) {
		return this.getPlace(this.parseToken(token));
	}

	@Override
	public String getToken(P place) {
		return this.createToken(this.getTokenParameters(place));
	}

	private String[] parseToken(String token) {
		return token.split(PARAM_SEPARATOR);
	}

	private String createToken(String[] parameters) {
		String token = "";
		for (String parameter : parameters) {
			token += parameter + PARAM_SEPARATOR;
		}
		return token;
	}
}