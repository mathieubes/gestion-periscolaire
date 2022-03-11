package api.controllers;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.models.JSONResponse;
import api.models.User;
import api.services.FakeDatabaseService;

@RestController
@RequestMapping("/users")
public class UserController {
  // #region Properties
  ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  FakeDatabaseService fakeDatabaseService;
  // #endregion

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping
  public JSONResponse getUsers() {
    List<User> users = fakeDatabaseService.getUsers();
    return new JSONResponse("usersList", users);
  }

}