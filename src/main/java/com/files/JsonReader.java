package com.files;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonReader {

    private String filePath = null;

    public JsonReader(String filePath) {
        this.filePath = filePath;
    }

    public JsonObject read() {
        Gson gson = new Gson();

        FileReader fileReader = null;

        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JsonObject jo = gson.fromJson(fileReader, JsonObject.class);

        return jo;
    }

}
