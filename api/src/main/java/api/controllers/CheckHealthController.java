package api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.services.JsonParserService;

@RestController
@RequestMapping("/")
public class CheckHealthController {
  @Autowired
  JsonParserService jsonParserService;

  @GetMapping
  public String checkHealth() throws JsonProcessingException {
    return jsonParserService.parseToJson("status", "Ok");
  }

}