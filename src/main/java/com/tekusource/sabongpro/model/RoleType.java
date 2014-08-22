package com.tekusource.sabongpro.model;

public enum RoleType {

	ADMIN("ADMIN"), GUEST("GUEST");
	private final String description;

	private RoleType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
