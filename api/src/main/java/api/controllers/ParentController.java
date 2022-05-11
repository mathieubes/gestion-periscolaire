package api.controllers;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import api.models.http.AllergenPostDTO;
import api.models.http.ChildPostDTO;
import api.models.http.SigninPostDTO;
import api.models.http.UserPostDTO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import api.models.Child;
import api.models.Parent;
import api.models.activities.restaurant.Allergen;
import api.services.ParentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users/parents")
public class ParentController {

  // #region Properties
  @Autowired
  ParentService parentService;

  private ObjectMapper objectMapper = new ObjectMapper();
  // #endregion

  // #region Get parents
  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<String> getParents() throws JsonProcessingException {
    try {
      ArrayList<Parent> parents = parentService.getParents();
      String toReturn = this.objectMapper.writeValueAsString(parents);
      return ResponseEntity.ok(toReturn);
    } catch (Exception e) {
      throw e;
    }
  }
  // #endregion

  // #region Add parent
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<String> addParent(@Valid @RequestBody UserPostDTO userPostDTO) throws JsonProcessingException {
    try {
      Parent toReturn = parentService.addParent(userPostDTO);
      return ResponseEntity.ok(this.objectMapper.writeValueAsString(toReturn));
    } catch (Exception e) {
      throw e;
    }
  }
  // #endregion

  // #region Update existing parent
  @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
  public ResponseEntity<String> updateParent(@Valid @RequestBody UserPostDTO userPostDTO, @PathVariable String id)
      throws JsonProcessingException {
    try {
      Parent toReturn = parentService.updateParent(userPostDTO, id);
      return ResponseEntity.ok(this.objectMapper.writeValueAsString(toReturn));
    } catch (Exception e) {
      throw e;
    }
  }
  // #endregion

  // #region Add child
  @RequestMapping(value = "/{id}/children/add", method = RequestMethod.POST)
  public ResponseEntity<String> addChild(@Valid @RequestBody ChildPostDTO childPostDTO, @PathVariable String id)
      throws JsonProcessingException {
    try {
      ArrayList<Child> toReturn = parentService.addChild(childPostDTO, id);
      return ResponseEntity.ok(this.objectMapper.writeValueAsString(toReturn));
    } catch (Exception e) {
      throw e;
    }
  }
  // #endregion

  // #region Update existing child
  @RequestMapping(value = "/{id}/children/{childId}/update", method = RequestMethod.POST)
  public ResponseEntity<String> updateChild(@Valid @RequestBody ChildPostDTO childPostDTO, @PathVariable String id,
      @PathVariable String childId)
      throws JsonProcessingException {
    try {
      Child toReturn = parentService.updateChild(childPostDTO, id, childId);
      return ResponseEntity.ok(this.objectMapper.writeValueAsString(toReturn));
    } catch (Exception e) {
      throw e;
    }
  }
  // #endregion

  // #region Delete parent
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> deleteParent(@PathVariable String id) {
    try {
      parentService.deleteParent(id);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      throw e;
    }
  }
  // #endregion

  // #region Signin
  @RequestMapping(value = "/signin", method = RequestMethod.POST)
  public ResponseEntity<String> signin(@RequestBody SigninPostDTO signinPostDTO) throws JsonProcessingException {
    try {
      Optional<Parent> toReturn = parentService.areSigninCredentialsCorrect(signinPostDTO);
      if (toReturn.isPresent()) {
        return ResponseEntity.ok(this.objectMapper.writeValueAsString(toReturn.get()));
      } else {
        return ResponseEntity.badRequest().build();
      }
    } catch (Exception e) {
      throw e;
    }
  }
  // #endregion

  // #region Get coef number
  @RequestMapping(value = "/{id}/fiscal", method = RequestMethod.GET)
  public ResponseEntity<String> getParentCoef(@PathVariable("id") String parentID,
      @RequestParam(name = "annualIncome") Double annualIncome) throws JsonProcessingException {
    try {
      final var parent = this.parentService.getParentByID(parentID);

      String toReturn = this.objectMapper.writeValueAsString(parent.getCoefNumber(annualIncome));
      return ResponseEntity.ok(toReturn);
    } catch (Exception e) {
      throw e;
    }
  }
  // #endregion

  // #region Register child to activity
  @RequestMapping(value = "/{id}/children/{childId}/activities/{activityId}/add", method = RequestMethod.POST)
  public ResponseEntity<String> registerChildToActivity(
      @PathVariable String id,
      @PathVariable String childId,
      @PathVariable String activityId)
      throws JsonProcessingException {
    try {
      this.parentService.registerChildToActivity(id, childId, activityId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      throw e;
    }
  }
  // #endregion

  // #region Unsuscribe child to activity
  @RequestMapping(value = "/{id}/children/{childId}/activities/{activityId}", method = RequestMethod.DELETE)
  public ResponseEntity<String> unsuscribeChildFromActivity(
      @PathVariable String id,
      @PathVariable String childId,
      @PathVariable String activityId)
      throws JsonProcessingException {
    try {
      this.parentService.unsuscribeChildFromActivity(id, childId, activityId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      throw e;
    }
  }
  // #endregion

  // #region Add allergen to child
  @RequestMapping(value = "/{id}/children/{childId}/allergens", method = RequestMethod.POST)
  public ResponseEntity<String> addAllergenToChild(
      @PathVariable String id,
      @PathVariable String childId,
      @Valid @RequestBody AllergenPostDTO allergenPostDTO)
      throws JsonProcessingException {
    try {
      this.parentService.addAllergenToChild(id, childId, allergenPostDTO);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      throw e;
    }
  }
  // #endregion

  // #region Remove allergen from child
  @RequestMapping(value = "/{id}/children/{childId}/allergens", method = RequestMethod.DELETE)
  public ResponseEntity<String> removeAllergenFromChild(
      @PathVariable String id,
      @PathVariable String childId,
      @RequestBody Allergen allergen)
      throws JsonProcessingException {
    try {
      this.parentService.removeAllergenFromChild(id, childId, allergen);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      throw e;
    }
  }
  // #endregion
}