package com.config;

import com.files.JsonReader;
import com.google.gson.JsonObject;

public class Config {

	private static JsonObject jo = null;

	public static String getReportName() {
		return getJsonObject().get("report").getAsJsonObject().get("name").getAsString();
	}

	public static String getReportTitle() {
		return getJsonObject().get("report").getAsJsonObject().get("title").getAsString();
	}

	public static String getReportTheme() {
		return getJsonObject().get("report").getAsJsonObject().get("theme").getAsString();
	}

	public static String getAuthorName() {
		return getJsonObject().get("author").getAsJsonObject().get("name").getAsString();
	}

	public static String getAutherGithubUsername() {
		return getJsonObject().get("author").getAsJsonObject().get("github-username").getAsString();
	}

	public static String getAuthorEmail() {
		return getJsonObject().get("author").getAsJsonObject().get("email").getAsString();
	}

	private static JsonObject getJsonObject() {

		if (jo == null) {
			JsonReader jsonReader = new JsonReader(Paths.JSON_CONFIG_FILE);
			jo = jsonReader.read();
		}

		return jo;
	}
}
