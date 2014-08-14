package com.tekusource.sabongpro.model;

public enum StatusType {
	
	ACTIVE("Active"), INACTIVE("Inactive");
	private final String description;

	private StatusType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
