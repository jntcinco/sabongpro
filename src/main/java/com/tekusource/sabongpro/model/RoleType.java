package com.tekusource.sabongpro.model;

public enum RoleType {

	ADMIN("Admin"), GUEST("Guest");
	private final String description;

	private RoleType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
