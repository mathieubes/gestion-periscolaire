package fr.univevry.api.controllers;

import fr.univevry.api.models.User;
import fr.univevry.api.services.FakeDatabaseService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public User[] getUsers() {
        return FakeDatabaseService.getUsers();
    }

}
