package com.tekusource.sabongpro.model;

public enum StreamingStatusType {

	SHOWING("Showing"), COMING("Coming"), NEXT("Next");
	private final String description;

	private StreamingStatusType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
