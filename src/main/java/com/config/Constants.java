package com.config;

public class Constants {
	
	public static final String USERS_ENDPOINT = "/users";

	private static final String JSON_SERVER_FILE_NAME = "db.json";

	public static final String JSON_SERVER_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/json/";

	public static final String JSON_SERVER_START_COMMAND = "json-server --watch " + JSON_SERVER_DIRECTORY + "/"
			+ JSON_SERVER_FILE_NAME;

	public static final String JSON_SERVER_STOP_COMMAND = "kill $(lsof -t -i:3000)";

}
