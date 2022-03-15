package api.controllers;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.models.Parent;
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
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String getUsers() throws JsonProcessingException {
    ArrayList<User> users = fakeDatabaseService.getUsers();
    return jsonParserService.parseToJson("users", users);

  }

  @RequestMapping(value = "/parents", method = RequestMethod.GET)
  public String getParents() throws JsonProcessingException {
    ArrayList<Parent> parents = fakeDatabaseService.getParents();
    return jsonParserService.parseToJson("parents", parents);
  }

}