package api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("")
public class CheckHealthController {
  private ObjectMapper objectMapper = new ObjectMapper();

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<String> checkHealth() throws JsonProcessingException {
    String toReturn = this.objectMapper.writeValueAsString("Health ok");
    return ResponseEntity.ok(toReturn);
  }

}