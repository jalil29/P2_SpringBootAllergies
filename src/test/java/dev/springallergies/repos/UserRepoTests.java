package dev.springallergies.repos;

import dev.springallergies.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepoTests {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void create_user_test(){

        User newUser = new User(0,"Dummy1","pass1234");
        userRepo.save(newUser);
        System.out.println(newUser);
        Assertions.assertNotEquals(0, newUser.getUserId());
    }

}
