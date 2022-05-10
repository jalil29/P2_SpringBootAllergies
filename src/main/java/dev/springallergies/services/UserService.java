package dev.springallergies.services;

import dev.springallergies.entities.User;

import java.util.List;

public interface UserService {

    User registerUser(User user);
    List<User> retrieveUsers();
    User getUserByIdNo(int userId);

}
