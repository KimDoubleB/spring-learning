package com.version.api.accept;


public class CustomMediaType {

	public static final CustomMediaType DOUBLE_B_API_VERSION_1;
	public static final String DOUBLE_B_API_VERSION_1_VALUE = "application/double.b.api.v1+json";

	public static final CustomMediaType DOUBLE_B_API_VERSION_2;
	public static final String DOUBLE_B_API_VERSION_2_VALUE = "application/double.b.api.v2+json";

	static {
		DOUBLE_B_API_VERSION_1 = new CustomMediaType(DOUBLE_B_API_VERSION_1_VALUE);
		DOUBLE_B_API_VERSION_2 = new CustomMediaType(DOUBLE_B_API_VERSION_2_VALUE);
	}

	private final String type;

	public CustomMediaType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
