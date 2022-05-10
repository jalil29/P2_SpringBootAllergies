package dev.springallergies.repos;

import dev.springallergies.entities.Potlukk;
import dev.springallergies.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;

@SpringBootTest
class PotlukkRepoTests {
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PotlukkRepo potlukkRepo;

    @Test
    void create_potlukk_test() {
        User testUser = new User(0, "test", "1234");
        userRepo.save(testUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        potlukkRepo.save(potlukk);

        System.out.println(potlukk);
        Assertions.assertNotEquals(0, potlukk.getPid());

        userRepo.delete(testUser);
        potlukkRepo.delete(potlukk);
    }

    @Test
    void get_potlukks_test() {
        User testUser = new User(0, "test", "1234");
        userRepo.save(testUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        potlukkRepo.save(potlukk);

        Potlukk otherPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        potlukkRepo.save(otherPotlukk);

        List<Potlukk> retrieved = potlukkRepo.findAll();

        Assertions.assertTrue(retrieved.size() > 1);

        potlukkRepo.delete(otherPotlukk);
        potlukkRepo.delete(potlukk);
        userRepo.delete(testUser);
    }

    @Test
    void update_potlukks_test() {
        User testUser = new User(0, "test", "1234");
        userRepo.save(testUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        potlukkRepo.save(potlukk);

        BigInteger newTime = BigInteger.valueOf(System.currentTimeMillis() - (7L*24L*60L*60L*1000L));
        // Remove ~a week from the current time.
        potlukk.setTime(newTime);
        potlukkRepo.save(potlukk);

        Potlukk retrieved = potlukkRepo.getById(potlukk.getPid());
        Assertions.assertEquals(newTime, potlukk.getTime());

        potlukkRepo.delete(potlukk);
        userRepo.delete(testUser);
    }

    @Test
    void delete_potlukks_test() {
        User testUser = new User(0, "test", "1234");
        userRepo.save(testUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        potlukkRepo.save(potlukk);
        Assertions.assertTrue(potlukkRepo.findById(potlukk.getPid()).isPresent());

        potlukkRepo.delete(potlukk);
        Assertions.assertFalse(potlukkRepo.findById(potlukk.getPid()).isPresent());

        potlukkRepo.delete(potlukk);
        userRepo.delete(testUser);
    }

    @Test
    void find_potlukk_by_creator() {
        User testUser = new User(0, "test", "1234");
        userRepo.save(testUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        potlukkRepo.save(potlukk);

        Potlukk otherPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        potlukkRepo.save(otherPotlukk);

        List<Potlukk> retrieved = potlukkRepo.findPotlukkByCreatorid(testUser.getUserId());
        Assertions.assertTrue(retrieved.size() > 1);

        potlukkRepo.delete(potlukk);
        potlukkRepo.delete(otherPotlukk);
        userRepo.delete(testUser);
    }

    @Test
    void find_potlukk_by_time() {
        BigInteger time = BigInteger.valueOf(System.currentTimeMillis());

        User testUser = new User(0, "test", "1234");
        userRepo.save(testUser);

        Potlukk potlukk = new Potlukk(0, time, testUser.getUserId());
        potlukkRepo.save(potlukk);

        Potlukk otherPotlukk = new Potlukk(0, time, testUser.getUserId());
        potlukkRepo.save(otherPotlukk);

        List<Potlukk> retrieved = potlukkRepo.findPotlukkByTime(time);

        Assertions.assertTrue(retrieved.size() > 1);

        potlukkRepo.delete(otherPotlukk);
        potlukkRepo.delete(potlukk);
        userRepo.delete(testUser);
    }

    @Test
    void delete_potlukk_cascade_test() {
        User testUser = new User(0, "test", "1234");
        userRepo.save(testUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        potlukkRepo.save(potlukk);

        userRepo.delete(testUser);
        Assertions.assertFalse(potlukkRepo.findById(potlukk.getPid()).isPresent());
        Assertions.assertFalse(userRepo.findById(testUser.getUserId()).isPresent());
    }
}
