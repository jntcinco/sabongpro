package com.tekusource.sabongpro.model;

public enum OddsType {

	TEN_TEN("10/10"), NINE_TEN("9/10"), EIGHT_TEN("8/10"), SIX_EIGHT("6/8"), EIGHT_ELEVEN("8/11");
	private final String description;

	private OddsType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
