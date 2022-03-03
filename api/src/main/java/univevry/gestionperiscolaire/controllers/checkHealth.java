package univevry.gestionperiscolaire.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class checkHealth {

  @GetMapping()
  public String returnHealth() {
    return "Ok";
  }

}
