package fr.univevry.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JSONResponse {
    private String key;
    private String message;

    public JSONResponse(String key, String message) {
        this.key = key;
        this.message = message;
    }

    public String getKey() {
        return this.key;
    }

    public String getMessage() {
        return this.message;
    }

}
