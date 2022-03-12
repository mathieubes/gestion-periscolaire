package api.models;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONResponse {
  private String key;
  private Object message;
  ObjectMapper objectMapper = new ObjectMapper();

  public JSONResponse(String key, Object message) {
    this.key = key;
    this.message = message;
  }

  public String getKey() {
    return this.key;
  }

  public String getMessage() throws JsonParseException, IOException {
    return (this.key + " : " + objectMapper.writeValueAsString(this.message));
  }

}
