package api.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import api.models.http.SigninPostDTO;
import api.models.http.UserPostDTO;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

  @RequestMapping(value = "/parents", method = RequestMethod.GET)
  public ResponseEntity<String> getParents() throws JsonProcessingException {
    try {
      ArrayList<Parent> parents = userService.getParents();
      String toReturn = jsonService.stringify("parents", parents);
      return ResponseEntity.ok(toReturn);

    } catch (Exception e) {
      throw e;
    }
  }

  @RequestMapping(value = "/parents/add", method = RequestMethod.POST)
  public ResponseEntity<Parent> addParent(@Valid @RequestBody UserPostDTO userPostDTO) {
    try {
      Parent toReturn = userService.addParent(userPostDTO);
      return ResponseEntity.ok(toReturn);
    } catch (Exception e) {
      throw e;
    }
  }

  @RequestMapping(value = "/parents/delete/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> deleteParent(@PathVariable String id) {
    try {
      userService.deleteParent(id);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      throw e;
    }
  }

  @RequestMapping(value = "/parents/signin", method = RequestMethod.POST)
  public ResponseEntity<Boolean> signin(@RequestBody SigninPostDTO signinPostDTO) {
    try {
      Boolean toReturn = userService.areSigninCredentialsCorrect(signinPostDTO);
      return ResponseEntity.ok(toReturn);
    } catch (Exception e) {
      return ResponseEntity.ok(false);
    }
  }

}