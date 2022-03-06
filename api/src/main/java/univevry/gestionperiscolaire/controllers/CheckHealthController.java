package univevry.gestionperiscolaire.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import univevry.gestionperiscolaire.models.JSONResponse;

@RestController
@RequestMapping("/")
public class CheckHealthController {

    @GetMapping
    public JSONResponse checkHealth() {
        return new JSONResponse("status", "OK");
    }

}