package dev.springallergies.controllers;

import dev.springallergies.dtos.LoginDTO;
import dev.springallergies.dtos.UserDTO;
import dev.springallergies.entities.User;
import dev.springallergies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin("https://main.d26a376lvucegi.amplifyapp.com/")
@Component
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public UserDTO userLogin(@RequestBody LoginDTO userInfo) {
        List<User> retrieved = userService.retrieveUsersByUsername(userInfo.getUsername());
        List<User> foundUsers = retrieved.stream().
                filter(u -> u.getUserName().equals(userInfo.getUsername())
                           && u.getPassword().equals(userInfo.getPassword())
                ).collect(Collectors.toList());

        if (foundUsers.size() <= 0) {
            return new UserDTO();
        }

        User user = foundUsers.get(0);
        return new UserDTO(user.getUserId(), user.getUserName());
    }

    @PostMapping("/users")
    @ResponseBody
    public UserDTO createUser(@RequestBody LoginDTO newUserInfo) {
        User newUser = new User();
        newUser.setUserId(0);
        newUser.setUserName(newUserInfo.getUsername());
        newUser.setPassword(newUserInfo.getPassword());

        User received = userService.registerUser(newUser);

        if (received == null || received.getUserId() == 0) {
            return new UserDTO();
        }

        return new UserDTO(received.getUserId(), received.getUserName());
    }

}
