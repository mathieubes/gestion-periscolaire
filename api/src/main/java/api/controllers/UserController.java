package api.controllers;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.models.User;
import api.services.FakeDatabaseService;
import api.services.JsonParserService;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  JsonParserService jsonParserService;

  @Autowired
  FakeDatabaseService fakeDatabaseService;

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping
  public String getUsers() throws JsonProcessingException {
    ArrayList<User> users = fakeDatabaseService.getUsers();
    return jsonParserService.parseToJson("users", users);

  }

}