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

        userRepo.save(newUser);
        System.out.println(newUser);

        Assertions.assertNotEquals(0, newUser.getUserId());
        Assertions.assertNotNull(userRepo.findById(newUser.getUserId()).orElse(null));

        userRepo.delete(newUser);
    }

    @Test
    void get_user_by_username() {
        User newUser = new User(0, "testname", "1234");
        userRepo.save(newUser);

        List<User> retrieved = userRepo.findUserByUserName("testname");
        retrieved.stream().forEach(System.out::println);
        Assertions.assertTrue(retrieved.size() > 0);

        userRepo.delete(newUser);
    }

    @Test
    void get_users_test() {
        User newUser = new User(0, "testing", "1234");
        User secondUser = new User(0, "testing", "1234");

        userRepo.save(newUser);
        userRepo.save(secondUser);

        List<User> retrieved = userRepo.findAll();
        Assertions.assertTrue(retrieved.size() > 1);

        userRepo.delete(newUser);
        userRepo.delete(secondUser);
    }

    @Test
    void update_users_test() {
        User newUser = new User(0, "testing", "1234");

        userRepo.save(newUser);

        System.out.println(newUser);
        newUser.setUserName("TESTING");

        userRepo.save(newUser);

        User retrieved = userRepo.findById(newUser.getUserId()).orElse(new User(0, "", ""));
        Assertions.assertEquals(newUser.getUserName(), retrieved.getUserName());
    }

    @Test
    void delete_users_test() {
        User newUser = new User(0, "testing", "1234");

        userRepo.save(newUser);

        Assertions.assertTrue(userRepo.findById(newUser.getUserId()).isPresent());

        userRepo.delete(newUser);

        Assertions.assertFalse(userRepo.findById(newUser.getUserId()).isPresent());
    }

}
