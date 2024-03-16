package com.base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Base {

	private JsonObject jsonObject = null;

	public Base() {

		String jsonFilePath = System.getProperty("user.dir") + "/config.json";

		if (jsonObject == null) {
			FileReader reader = null;
			Gson gson = new Gson();

			try {
				reader = new FileReader(jsonFilePath);
				jsonObject = gson.fromJson(reader, JsonObject.class);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}
	
	public String getApiUrl() {
		return jsonObject.get("api_url").getAsString();
	}
}
