package fr.univevry.api.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import fr.univevry.api.models.JSONResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CheckHealthController {

    @GetMapping
    public JSONResponse checkHealth() {
        return new JSONResponse("status", "OK");
    }

}
