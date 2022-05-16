package dev.springallergies.repos;

import dev.springallergies.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserRepoTests {

    @Autowired
    private UserRepo userRepo;

    @Test
    void create_user_test(){
        User newUser = new User(0,"Dummy1","pass1234");

        User retrievedUser = userRepo.save(newUser);

        Assertions.assertNotEquals(0, retrievedUser.getUserId());
        Assertions.assertNotNull(userRepo.findById(retrievedUser.getUserId()).orElse(null));

        userRepo.delete(newUser);
    }

    @Test
    void get_user_by_username() {
        User newUser = new User(0, "testname", "1234");
        User retrievedUser = userRepo.save(newUser);

        List<User> retrieved = userRepo.findByUserName("testname");
        retrieved.stream().forEach(System.out::println);
        Assertions.assertTrue(retrieved.size() > 0);

        userRepo.delete(retrievedUser);
    }

    @Test
    void get_users_test() {
        User newUser = new User(0, "testing", "1234");
        User secondUser = new User(0, "testing", "1234");

        User retrievedUser1 = userRepo.save(newUser);
        User retrievedUser2 =userRepo.save(secondUser);

        List<User> retrieved = userRepo.findAll();
        Assertions.assertTrue(retrieved.size() > 1);

        userRepo.delete(retrievedUser1);
        userRepo.delete(retrievedUser2);
    }

    @Test
    void update_users_test() {
        User newUser = new User(0, "testing", "1234");

        User retrievedUser =  userRepo.save(newUser);

        userRepo.save(retrievedUser);

        User retrieved = userRepo.findById(retrievedUser.getUserId()).orElse(new User(0, "", ""));

        Assertions.assertEquals(retrievedUser.getUserName(), retrieved.getUserName());
        userRepo.delete(retrievedUser);
    }

    @Test
    void delete_users_test() {
        User newUser = new User(0, "testing", "1234");

        User retrievedUser = userRepo.save(newUser);

        Assertions.assertTrue(userRepo.findById(retrievedUser.getUserId()).isPresent());

        userRepo.delete(retrievedUser);

        Assertions.assertFalse(userRepo.findById(retrievedUser.getUserId()).isPresent());
    }

}
