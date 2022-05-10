package dev.springallergies.controllers;

import dev.springallergies.entities.User;
import dev.springallergies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    @ResponseBody
    public User postUser(@RequestBody User user){
        return this.userService.registerUser(user);
    }

}
