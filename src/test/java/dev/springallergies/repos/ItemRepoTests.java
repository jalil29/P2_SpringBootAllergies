package dev.springallergies.repos;

import dev.springallergies.entities.Item;
import dev.springallergies.entities.Potlukk;
import dev.springallergies.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;

@SpringBootTest
class ItemRepoTests {
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PotlukkRepo potlukkRepo;

    @Test
    void create_item_test(){
        User testUser = new User(0, "test", "1234");
        userRepo.save(testUser);

        Potlukk newPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        potlukkRepo.save(newPotlukk);

        Item newItem = new Item(0, "ownerWanted", "Pasta salad", "Nobody", newPotlukk.getPid());
        itemRepo.save(newItem);

        System.out.println(newItem);
        Assertions.assertNotEquals(0, newItem.getItemId());

        itemRepo.delete(newItem);
        potlukkRepo.delete(newPotlukk);
        userRepo.delete(testUser);

    }

    @Test
    void get_items_test() {
        User testUser = new User(0, "test", "1234");
        userRepo.save(testUser);

        Potlukk newPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        potlukkRepo.save(newPotlukk);

        Item newItem = new Item(0, "ownerWanted", "Pasta salad", null, newPotlukk.getPid());
        itemRepo.save(newItem);

        Item otherItem = new Item(0, "guestProvided", "Sandwiches", null, newPotlukk.getPid());
        itemRepo.save(otherItem);

        List<Item> retrieved = itemRepo.findAll();
        Assertions.assertTrue(retrieved.size() > 1);

        itemRepo.delete(otherItem);
        itemRepo.delete(newItem);
        potlukkRepo.delete(newPotlukk);
        userRepo.delete(testUser);
    }

    @Test
    void update_items_test() {
        User testUser = new User(0, "test", "1234");
        userRepo.save(testUser);

        Potlukk newPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        potlukkRepo.save(newPotlukk);

        Item newItem = new Item(0, "ownerWanted", "Pasta salad", "Nobody", newPotlukk.getPid());
        itemRepo.save(newItem);

        newItem.setStatus("guestProvided");
        itemRepo.save(newItem);

        Item retrieved = itemRepo.findById(newItem.getItemId()).orElse(new Item(0, "", "", "", 0));
        Assertions.assertEquals(newItem.getStatus(), retrieved.getStatus());

        itemRepo.delete(newItem);
        potlukkRepo.delete(newPotlukk);
        userRepo.delete(testUser);
    }

    @Test
    void delete_items_test() {
        User testUser = new User(0, "test", "1234");
        userRepo.save(testUser);

        Potlukk newPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        potlukkRepo.save(newPotlukk);

        Item newItem = new Item(0, "ownerWanted", "Pasta salad", "Nobody", newPotlukk.getPid());
        itemRepo.save(newItem);

        Assertions.assertTrue(itemRepo.findById(newItem.getItemId()).isPresent());

        itemRepo.delete(newItem);
        Assertions.assertFalse(itemRepo.findById(newItem.getItemId()).isPresent());

        potlukkRepo.delete(newPotlukk);
        userRepo.delete(testUser);
    }

    @Test
    void delete_items_cascade() {
        User testUser = new User(0, "test", "1234");
        userRepo.save(testUser);

        Potlukk newPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        potlukkRepo.save(newPotlukk);

        Item newItem = new Item(0, "ownerWanted", "Pasta salad", "Nobody", newPotlukk.getPid());
        itemRepo.save(newItem);

        userRepo.delete(testUser);
        Assertions.assertFalse(potlukkRepo.findById(newPotlukk.getPid()).isPresent());
        Assertions.assertFalse(itemRepo.findById(newItem.getItemId()).isPresent());
    }

    @Test
    void find_items_by_status() {
        User testUser = new User(0, "test", "1234");
        userRepo.save(testUser);

        Potlukk newPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        potlukkRepo.save(newPotlukk);

        Item newItem = new Item(0, "guestProvided", "Pasta salad", "Nobody", newPotlukk.getPid());
        itemRepo.save(newItem);
        Item otherItem = new Item(0, "guestProvided", "Sandwiches", "A Person", newPotlukk.getPid());
        itemRepo.save(otherItem);

        List<Item> retrieved = itemRepo.findByStatus("guestProvided");
        Assertions.assertTrue(retrieved.size() > 1);

        itemRepo.delete(otherItem);
        itemRepo.delete(newItem);
        potlukkRepo.delete(newPotlukk);
        userRepo.delete(testUser);
    }

    @Test
    void find_items_by_supplier() {
        User testUser = new User(0, "test", "1234");
        userRepo.save(testUser);

        Potlukk newPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        potlukkRepo.save(newPotlukk);

        Item newItem = new Item(0, "ownerWanted", "Pasta salad", "A Person", newPotlukk.getPid());
        itemRepo.save(newItem);
        Item otherItem = new Item(0, "guestProvided", "Sandwiches", "A Person", newPotlukk.getPid());
        itemRepo.save(otherItem);

        List<Item> retrieved = itemRepo.findBySupplier("A Person");
        Assertions.assertTrue(retrieved.size() > 1);

        itemRepo.delete(otherItem);
        itemRepo.delete(newItem);
        potlukkRepo.delete(newPotlukk);
        userRepo.delete(testUser);
    }

    @Test
    void find_items_by_pid() {
        User testUser = new User(0, "test", "1234");
        userRepo.save(testUser);

        Potlukk newPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        potlukkRepo.save(newPotlukk);

        Item newItem = new Item(0, "ownerWanted", "Pasta salad", "Nobody", newPotlukk.getPid());
        itemRepo.save(newItem);
        Item otherItem = new Item(0, "guestProvided", "Sandwiches", null, newPotlukk.getPid());
        itemRepo.save(otherItem);

        List<Item> retrieved = itemRepo.findByPid(newPotlukk.getPid());
        Assertions.assertTrue(retrieved.size() > 1);

        itemRepo.delete(otherItem);
        itemRepo.delete(newItem);
        potlukkRepo.delete(newPotlukk);
        userRepo.delete(testUser);
    }
}
