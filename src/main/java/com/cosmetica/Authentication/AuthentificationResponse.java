package com.cosmetica.Authentication;



public class AuthentificationResponse {

	private final String jwt;

	public String getJwt() {
		return jwt;
	}

	public AuthentificationResponse(String jwt) {
		this.jwt = jwt;
	}
}

