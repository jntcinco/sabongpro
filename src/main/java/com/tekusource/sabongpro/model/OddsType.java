package com.tekusource.sabongpro.model;

public enum OddsType {

	TEN_TEN("TEN_TEN"), NINE_TEN("NINE_TEN"), EIGHT_TEN("EIGHT_TEN"), TEN_NINE("TEN_NINE"), TEN_EIGHT("TEN_EIGHT");
	private final String description;

	private OddsType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
