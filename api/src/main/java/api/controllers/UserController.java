package api.controllers;

import java.util.ArrayList;

import api.models.http.UserPostDTO;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import api.models.Parent;
import api.services.UserService;
import api.services.JsonService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  JsonService jsonService;

  @Autowired
  UserService userService;

  @RequestMapping(value = "/parents/add", method = RequestMethod.POST)
  public Parent addParent(@RequestBody UserPostDTO userPostDTO) {
    return userService.addParent(userPostDTO);
  }

  @RequestMapping(value = "/parents", method = RequestMethod.GET)
  public String getParents() throws JsonProcessingException {
    ArrayList<Parent> parents = userService.getParents();
    return jsonService.stringify("parents", parents);
  }

}