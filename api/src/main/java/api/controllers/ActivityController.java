
package api.controllers;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.models.activities.Activity;
import api.services.ActivityService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/activities")
public class ActivityController {
  // #region Properties
  @Autowired
  ActivityService activityService;

  private ObjectMapper objectMapper = new ObjectMapper();
  // #endregion

  // #region Get activities
  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<String> getActivities() throws JsonProcessingException {
    try {
      ArrayList<Activity> activities = activityService.getActivities();
      String toReturn = this.objectMapper.writeValueAsString(activities);
      return ResponseEntity.ok(toReturn);
    } catch (Exception e) {
      throw e;
    }
  }
}
