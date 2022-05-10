package dev.springallergies;

import dev.springallergies.entities.User;
import dev.springallergies.repos.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Table;

@SpringBootTest
public class UserRepoTests {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void create_user_test(){
        /*how to pass in new user with params using lombok, not sure on syntax short of writing out the entire constructor just not sure if theres some short hand*/
        User newUser = new User();

    }

}
