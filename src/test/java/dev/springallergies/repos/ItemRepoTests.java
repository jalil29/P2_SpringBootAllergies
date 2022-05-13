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
        User retrievedUser = userRepo.save(testUser);

        Potlukk newPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        Potlukk retrievedPotlukk = potlukkRepo.save(newPotlukk);

        Item newItem = new Item(0, "ownerWanted", "Pasta salad", "Nobody", newPotlukk.getPid());
        Item receivedItem = itemRepo.save(newItem);

        System.out.println(newItem);
        Assertions.assertNotEquals(0, receivedItem.getItemId());

        itemRepo.delete(receivedItem);
        potlukkRepo.delete(retrievedPotlukk);
        userRepo.delete(retrievedUser);
    }

    @Test
    void get_items_test() {
        User testUser = new User(0, "test", "1234");
        User retrievedUser = userRepo.save(testUser);

        Potlukk newPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        Potlukk retrievedPotlukk = potlukkRepo.save(newPotlukk);

        Item newItem = new Item(0, "ownerWanted", "Pasta salad", "Nobody", newPotlukk.getPid());
        Item receivedItem = itemRepo.save(newItem);
        Item otherItem = new Item(0, "guestProvided", "Sandwiches", null, newPotlukk.getPid());
        Item receivedOtherItem = itemRepo.save(otherItem);

        List<Item> retrieved = itemRepo.findAll();
        Assertions.assertTrue(retrieved.size() > 1);

        itemRepo.delete(receivedItem);
        itemRepo.delete(receivedOtherItem);
        potlukkRepo.delete(retrievedPotlukk);
        userRepo.delete(retrievedUser);
    }

    @Test
    void update_items_test() {
        User testUser = new User(0, "test", "1234");
        User retrievedUser = userRepo.save(testUser);

        Potlukk newPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        Potlukk retrievedPotlukk = potlukkRepo.save(newPotlukk);

        Item newItem = new Item(0, "ownerWanted", "Pasta salad", "Nobody", newPotlukk.getPid());
        Item receivedItem = itemRepo.save(newItem);

        receivedItem.setStatus("guestProvided");
        itemRepo.save(newItem);

        Item retrieved = itemRepo.findById(newItem.getItemId()).orElse(new Item(0, "", "", "", 0));
        Assertions.assertEquals(receivedItem.getStatus(), retrieved.getStatus());

        itemRepo.delete(receivedItem);
        potlukkRepo.delete(retrievedPotlukk);
        userRepo.delete(retrievedUser);
    }

    @Test
    void delete_items_test() {
        User testUser = new User(0, "test", "1234");
        User retrievedUser = userRepo.save(testUser);

        Potlukk newPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        Potlukk retrievedPotlukk = potlukkRepo.save(newPotlukk);

        Item newItem = new Item(0, "ownerWanted", "Pasta salad", "Nobody", newPotlukk.getPid());
        Item receivedItem = itemRepo.save(newItem);

        Assertions.assertTrue(itemRepo.findById(receivedItem.getItemId()).isPresent());

        itemRepo.delete(receivedItem);
        Assertions.assertFalse(itemRepo.findById(receivedItem.getItemId()).isPresent());

        potlukkRepo.delete(retrievedPotlukk);
        userRepo.delete(retrievedUser);
    }

    @Test
    void delete_items_cascade() {
        User testUser = new User(0, "test", "1234");
        User retrievedUser = userRepo.save(testUser);

        Potlukk newPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedUser.getUserId());
        Potlukk retrievedPotlukk = potlukkRepo.save(newPotlukk);

        Item newItem = new Item(0, "ownerWanted", "Pasta salad", "Nobody", retrievedPotlukk.getPid());
        Item receivedItem = itemRepo.save(newItem);

        userRepo.delete(testUser);
        Assertions.assertFalse(potlukkRepo.findById(retrievedPotlukk.getPid()).isPresent());
        Assertions.assertFalse(itemRepo.findById(receivedItem.getItemId()).isPresent());
    }

    @Test
    void find_items_by_status() {
        User testUser = new User(0, "test", "1234");
        User retrievedUser = userRepo.save(testUser);

        Potlukk newPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        Potlukk retrievedPotlukk = potlukkRepo.save(newPotlukk);

        Item newItem = new Item(0, "ownerWanted", "Pasta salad", "Nobody", newPotlukk.getPid());
        Item receivedItem = itemRepo.save(newItem);
        Item otherItem = new Item(0, "guestProvided", "Sandwiches", null, newPotlukk.getPid());
        Item receivedOtherItem = itemRepo.save(otherItem);

        List<Item> retrieved = itemRepo.findByStatus("guestProvided");
        Assertions.assertTrue(retrieved.size() > 0);

        itemRepo.delete(receivedItem);
        itemRepo.delete(receivedOtherItem);
        potlukkRepo.delete(retrievedPotlukk);
        userRepo.delete(retrievedUser);
    }

    @Test
    void find_items_by_supplier() {
        User testUser = new User(0, "test", "1234");
        User retrievedUser = userRepo.save(testUser);

        Potlukk newPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        Potlukk retrievedPotlukk = potlukkRepo.save(newPotlukk);

        Item newItem = new Item(0, "ownerWanted", "Pasta salad", "Nobody", newPotlukk.getPid());
        Item receivedItem = itemRepo.save(newItem);
        Item otherItem = new Item(0, "guestProvided", "Sandwiches", null, newPotlukk.getPid());
        Item receivedOtherItem = itemRepo.save(otherItem);

        List<Item> retrieved = itemRepo.findBySupplier("Nobody");
        Assertions.assertTrue(retrieved.size() > 0);

        itemRepo.delete(receivedItem);
        itemRepo.delete(receivedOtherItem);
        potlukkRepo.delete(retrievedPotlukk);
        userRepo.delete(retrievedUser);
    }

    @Test
    void find_items_by_pid() {
        User testUser = new User(0, "test", "1234");
        User retrievedUser = userRepo.save(testUser);

        Potlukk newPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), testUser.getUserId());
        Potlukk retrievedPotlukk = potlukkRepo.save(newPotlukk);

        Item newItem = new Item(0, "ownerWanted", "Pasta salad", "Nobody", newPotlukk.getPid());
        Item receivedItem = itemRepo.save(newItem);
        Item otherItem = new Item(0, "guestProvided", "Sandwiches", null, newPotlukk.getPid());
        Item receivedOtherItem = itemRepo.save(otherItem);

        List<Item> retrieved = itemRepo.findByPid(newPotlukk.getPid());
        Assertions.assertTrue(retrieved.size() > 1);

        itemRepo.delete(receivedItem);
        itemRepo.delete(receivedOtherItem);
        potlukkRepo.delete(retrievedPotlukk);
        userRepo.delete(retrievedUser);
    }
}
