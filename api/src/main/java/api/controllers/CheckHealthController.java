package api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.services.JsonService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class CheckHealthController {
  @Autowired
  JsonService jsonService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ResponseEntity<String> checkHealth() throws JsonProcessingException {
    String toReturn = jsonService.stringify("status", "Ok");
    return ResponseEntity.ok(toReturn);
  }

}