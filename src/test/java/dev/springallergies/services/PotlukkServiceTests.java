package dev.springallergies.services;

import dev.springallergies.entities.Potlukk;
import dev.springallergies.entities.User;
import dev.springallergies.repos.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;

@SpringBootTest
class PotlukkServiceTests {
    @Autowired
    PotluckService potluckService;
    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Test
    void fetch_potlucks_by_id_test() {
        User user = new User(0, "Testing", "123455");
        User otherUser = new User(0, "Testign", "23456");

        User retrievedUser = userService.registerUser(user);
        User retrievedOtherUser = userService.registerUser(otherUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedUser.getUserId());
        Potlukk otherPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedOtherUser.getUserId());

        Potlukk retrievedPotlukk = potluckService.updatePotluck(potlukk);
        Potlukk retrievedOtherPotlukk = potluckService.updatePotluck(otherPotlukk);

        Potlukk received = potluckService.fetchPotluckByPotID(retrievedPotlukk.getPid());
        Assertions.assertNotNull(received);
        Assertions.assertEquals(retrievedPotlukk, received);

        received = potluckService.fetchPotluckByPotID(retrievedOtherPotlukk.getPid());
        Assertions.assertNotNull(received);
        Assertions.assertEquals(retrievedOtherPotlukk, received);

        potluckService.deletePotluck(potlukk);
        potluckService.deletePotluck(otherPotlukk);
        userRepo.delete(user);
        userRepo.delete(otherUser);
    }

    @Test
    void fetch_potlucks_by_user_test() {
        User user = new User(0, "Testing", "123455");
        User otherUser = new User(0, "Testign", "23456");

        User retrievedUser = userService.registerUser(user);
        User retrievedOtherUser = userService.registerUser(otherUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedUser.getUserId());
        Potlukk otherPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedOtherUser.getUserId());

        Potlukk retrievedPotlukk = potluckService.updatePotluck(potlukk);
        Potlukk retrievedOtherPotlukk = potluckService.updatePotluck(otherPotlukk);

        List<Potlukk> received = potluckService.fetchPotlucksByUserID(retrievedUser.getUserId());
        Assertions.assertTrue(received.size() > 0);

        potluckService.deletePotluck(potlukk);
        potluckService.deletePotluck(otherPotlukk);
        userRepo.delete(user);
        userRepo.delete(otherUser);
    }

    @Test
    void fetch_potlucks_test() {
        User user = new User(0, "Testing", "123455");
        User otherUser = new User(0, "Testign", "23456");

        User retrievedUser = userService.registerUser(user);
        User retrievedOtherUser = userService.registerUser(otherUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedUser.getUserId());
        Potlukk otherPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedOtherUser.getUserId());

        Potlukk retrievedPotlukk = potluckService.updatePotluck(potlukk);
        Potlukk retrievedOtherPotlukk = potluckService.updatePotluck(otherPotlukk);

        List<Potlukk> allPotlukks = potluckService.fetchPotlucks();
        Assertions.assertTrue(allPotlukks.size() > 1);

        potluckService.deletePotluck(potlukk);
        potluckService.deletePotluck(otherPotlukk);
        userRepo.delete(user);
        userRepo.delete(otherUser);
    }

    @Test
    void update_potluck_test() {
        User user = new User(0, "Testing", "123455");
        User otherUser = new User(0, "Testign", "23456");

        User retrievedUser = userService.registerUser(user);
        User retrievedOtherUser = userService.registerUser(otherUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedUser.getUserId());
        Potlukk otherPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedOtherUser.getUserId());

        Potlukk retrievedPotlukk = potluckService.updatePotluck(potlukk);
        Potlukk retrievedOtherPotlukk = potluckService.updatePotluck(otherPotlukk);

        retrievedPotlukk.setTime(BigInteger.ZERO);
        retrievedOtherPotlukk.setTime(BigInteger.ZERO);

        Potlukk received = potluckService.updatePotluck(retrievedPotlukk);
        Assertions.assertEquals(BigInteger.ZERO, received.getTime());

        received = potluckService.updatePotluck(retrievedOtherPotlukk);
        Assertions.assertEquals(BigInteger.ZERO, received.getTime());

        potluckService.deletePotluck(potlukk);
        potluckService.deletePotluck(otherPotlukk);
        userRepo.delete(user);
        userRepo.delete(otherUser);
    }

    @Test
    void delete_potluck_test() {
        User user = new User(0, "Testing", "123455");
        User otherUser = new User(0, "Testign", "23456");

        User retrievedUser = userService.registerUser(user);
        User retrievedOtherUser = userService.registerUser(otherUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedUser.getUserId());
        Potlukk otherPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedOtherUser.getUserId());

        Potlukk retrievedPotlukk = potluckService.updatePotluck(potlukk);
        Potlukk retrievedOtherPotlukk = potluckService.updatePotluck(otherPotlukk);

        potluckService.deletePotluck(retrievedPotlukk);
        potluckService.deletePotluck(retrievedOtherPotlukk);
        userRepo.delete(user);
        userRepo.delete(otherUser);
    }
}
