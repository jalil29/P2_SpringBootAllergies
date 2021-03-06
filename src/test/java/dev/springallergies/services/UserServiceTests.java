package dev.springallergies.services;

import dev.springallergies.entities.User;
import dev.springallergies.repos.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserServiceTests {
    @Autowired
    UserService service;
    @Autowired
    UserRepo repo;

    @Test
    void register_user_test() {
        User user = new User(0, "Testing", "123345");
        User retrieved = service.registerUser(user);
        Assertions.assertNotNull(retrieved);
        Assertions.assertNotEquals(0, user.getUserId());

        repo.delete(retrieved);
    }

    @Test
    void retrieve_users_test() {
        User user = new User(0, "Testing", "123455");
        User otherUser = new User(0, "Testign", "23456");

        User retrievedUser = service.registerUser(user);
        User retrievedOtherUser = service.registerUser(otherUser);

        List<User> users = service.retrieveUsers();
        Assertions.assertTrue(users.size() > 1);

        repo.delete(retrievedUser);
        repo.delete(retrievedOtherUser);
    }

    @Test
    void retrieve_user_by_username() {
        User user = new User(0, "Testing", "123455");
        User otherUser = new User(0, "Testign", "23456");

        User retrievedUser = service.registerUser(user);
        User retrievedOtherUser = service.registerUser(otherUser);

        User foundUser = null;
        List<User> foundUsers = service.retrieveUsersByUsername("Testign");
        for(User u : foundUsers){
            if (u.equals(retrievedOtherUser)) {
                foundUser = u;
            }
        }

        Assertions.assertTrue(foundUser != null);

        repo.delete(retrievedUser);
        repo.delete(retrievedOtherUser);
    }
    
    @Test
    void get_user_by_id_tests() {
        User user = new User(0, "Testing", "123455");
        User otherUser = new User(0, "Testign", "23456");

        User retrievedUser = service.registerUser(user);
        User retrievedOtherUser = service.registerUser(otherUser);

        Assertions.assertEquals(retrievedUser, service.getUserByIdNo(retrievedUser.getUserId()));
        Assertions.assertEquals(retrievedOtherUser, service.getUserByIdNo(retrievedOtherUser.getUserId()));

        repo.delete(retrievedUser);
        repo.delete(retrievedOtherUser);
    }
}
