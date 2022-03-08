package api.controllers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.models.User;
import api.services.FakeDatabaseService;

@RestController
@RequestMapping("/users")
public class UserController {

  ObjectMapper objectMapper = new ObjectMapper();

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping
  public String getUsers()
      throws JsonParseException, IOException {

    List<User> users = FakeDatabaseService.getUsers();
    return objectMapper.writeValueAsString(users);
  }

}