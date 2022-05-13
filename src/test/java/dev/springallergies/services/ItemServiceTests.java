package dev.springallergies.services;

import dev.springallergies.entities.Item;
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
public class ItemServiceTests {
    @Autowired
    ItemService itemService;
    @Autowired
    PotluckService potluckService;
    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Test
    void fetch_item_by_id_test() {
        User user = new User(0, "Testing", "123455");
        User otherUser = new User(0, "Testign", "23456");

        User retrievedUser = userService.registerUser(user);
        User retrievedOtherUser = userService.registerUser(otherUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedUser.getUserId());
        Potlukk otherPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedOtherUser.getUserId());

        Potlukk retrievedPotlukk = potluckService.updatePotluck(potlukk);
        Potlukk retrievedOtherPotlukk = potluckService.updatePotluck(otherPotlukk);

        Item item = new Item(0, "guestProvided", "Sandwich", "Person", retrievedPotlukk.getPid());
        Item otherItem = new Item(0, "ownerWanted", "Sandwich", null, retrievedOtherPotlukk.getPid());

        Item retrievedItem = itemService.updateItem(item);
        Item retrievedOtherItem = itemService.updateItem(otherItem);

        Item received = itemService.fetchItemByItemId(retrievedItem.getItemId());
        Assertions.assertNotNull(received);
        Assertions.assertEquals(received, retrievedItem);

        received = itemService.fetchItemByItemId(retrievedOtherItem.getItemId());
        Assertions.assertNotNull(received);
        Assertions.assertEquals(received, retrievedOtherItem);


        itemService.deleteItem(retrievedItem);
        itemService.deleteItem(retrievedOtherItem);
        potluckService.deletePotluck(retrievedPotlukk);
        potluckService.deletePotluck(retrievedOtherPotlukk);
        userRepo.delete(retrievedUser);
        userRepo.delete(retrievedOtherUser);
    }

    @Test
    void fetch_items_test() {
        User user = new User(0, "Testing", "123455");
        User otherUser = new User(0, "Testign", "23456");

        User retrievedUser = userService.registerUser(user);
        User retrievedOtherUser = userService.registerUser(otherUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedUser.getUserId());
        Potlukk otherPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedOtherUser.getUserId());

        Potlukk retrievedPotlukk = potluckService.updatePotluck(potlukk);
        Potlukk retrievedOtherPotlukk = potluckService.updatePotluck(otherPotlukk);

        Item item = new Item(0, "guestProvided", "Sandwich", "Person", retrievedPotlukk.getPid());
        Item otherItem = new Item(0, "ownerWanted", "Sandwich", null, retrievedOtherPotlukk.getPid());

        Item retrievedItem = itemService.updateItem(item);
        Item retrievedOtherItem = itemService.updateItem(otherItem);

        List<Item> items = itemService.fetchItems();
        Assertions.assertTrue(items.size()>1);

        itemService.deleteItem(retrievedItem);
        itemService.deleteItem(retrievedOtherItem);
        potluckService.deletePotluck(retrievedPotlukk);
        potluckService.deletePotluck(retrievedOtherPotlukk);
        userRepo.delete(retrievedUser);
        userRepo.delete(retrievedOtherUser);
    }

    @Test
    void fetch_items_by_potluck_test() {
        User user = new User(0, "Testing", "123455");
        User otherUser = new User(0, "Testign", "23456");

        User retrievedUser = userService.registerUser(user);
        User retrievedOtherUser = userService.registerUser(otherUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedUser.getUserId());
        Potlukk otherPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedOtherUser.getUserId());

        Potlukk retrievedPotlukk = potluckService.updatePotluck(potlukk);
        Potlukk retrievedOtherPotlukk = potluckService.updatePotluck(otherPotlukk);

        Item item = new Item(0, "guestProvided", "Sandwich", "Person", retrievedPotlukk.getPid());
        Item otherItem = new Item(0, "ownerWanted", "Sandwich", null, retrievedOtherPotlukk.getPid());

        Item retrievedItem = itemService.updateItem(item);
        Item retrievedOtherItem = itemService.updateItem(otherItem);

        List<Item> potlukkItems = itemService.fetchItemsByPid(retrievedPotlukk.getPid());
        Assertions.assertTrue(potlukkItems.size() > 0);

        potlukkItems = itemService.fetchItemsByPid(retrievedOtherPotlukk.getPid());
        Assertions.assertTrue(potlukkItems.size() > 0);

        itemService.deleteItem(retrievedItem);
        itemService.deleteItem(retrievedOtherItem);
        potluckService.deletePotluck(retrievedPotlukk);
        potluckService.deletePotluck(retrievedOtherPotlukk);
        userRepo.delete(retrievedUser);
        userRepo.delete(retrievedOtherUser);
    }

    @Test
    void fetch_items_by_status_test() {
        User user = new User(0, "Testing", "123455");
        User otherUser = new User(0, "Testign", "23456");

        User retrievedUser = userService.registerUser(user);
        User retrievedOtherUser = userService.registerUser(otherUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedUser.getUserId());
        Potlukk otherPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedOtherUser.getUserId());

        Potlukk retrievedPotlukk = potluckService.updatePotluck(potlukk);
        Potlukk retrievedOtherPotlukk = potluckService.updatePotluck(otherPotlukk);

        Item item = new Item(0, "guestProvided", "Sandwich", "Person", retrievedPotlukk.getPid());
        Item otherItem = new Item(0, "ownerWanted", "Sandwich", null, retrievedOtherPotlukk.getPid());

        Item retrievedItem = itemService.updateItem(item);
        Item retrievedOtherItem = itemService.updateItem(otherItem);

        List<Item> status = itemService.fetchItemsByStatus("guestProvided");
        Assertions.assertTrue(status.size() > 0);

        status = itemService.fetchItemsByStatus("guestProvided");
        Assertions.assertTrue(status.size() > 0);


        itemService.deleteItem(retrievedItem);
        itemService.deleteItem(retrievedOtherItem);
        potluckService.deletePotluck(retrievedPotlukk);
        potluckService.deletePotluck(retrievedOtherPotlukk);
        userRepo.delete(retrievedUser);
        userRepo.delete(retrievedOtherUser);
    }

    @Test
    void fetch_items_by_supplier_test() {
        User user = new User(0, "Testing", "123455");
        User otherUser = new User(0, "Testign", "23456");

        User retrievedUser = userService.registerUser(user);
        User retrievedOtherUser = userService.registerUser(otherUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedUser.getUserId());
        Potlukk otherPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedOtherUser.getUserId());

        Potlukk retrievedPotlukk = potluckService.updatePotluck(potlukk);
        Potlukk retrievedOtherPotlukk = potluckService.updatePotluck(otherPotlukk);

        Item item = new Item(0, "guestProvided", "Sandwich", "Person", retrievedPotlukk.getPid());
        Item otherItem = new Item(0, "ownerWanted", "Sandwich", null, retrievedOtherPotlukk.getPid());

        Item retrievedItem = itemService.updateItem(item);
        Item retrievedOtherItem = itemService.updateItem(otherItem);

        List<Item> suppliedItems = itemService.fetchItemsBySupplier("Person");
        Assertions.assertTrue(suppliedItems.size() > 0);

        itemService.deleteItem(retrievedItem);
        itemService.deleteItem(retrievedOtherItem);
        potluckService.deletePotluck(retrievedPotlukk);
        potluckService.deletePotluck(retrievedOtherPotlukk);
        userRepo.delete(retrievedUser);
        userRepo.delete(retrievedOtherUser);
    }

    @Test
    void update_item_test() {
        User user = new User(0, "Testing", "123455");
        User otherUser = new User(0, "Testign", "23456");

        User retrievedUser = userService.registerUser(user);
        User retrievedOtherUser = userService.registerUser(otherUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedUser.getUserId());
        Potlukk otherPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedOtherUser.getUserId());

        Potlukk retrievedPotlukk = potluckService.updatePotluck(potlukk);
        Potlukk retrievedOtherPotlukk = potluckService.updatePotluck(otherPotlukk);

        Item item = new Item(0, "guestProvided", "Sandwich", "Person", retrievedPotlukk.getPid());
        Item otherItem = new Item(0, "ownerWanted", "Sandwich", null, retrievedOtherPotlukk.getPid());

        Item retrievedItem = itemService.updateItem(item);
        Item retrievedOtherItem = itemService.updateItem(otherItem);

        retrievedItem.setStatus("ownerWanted");
        retrievedItem.setSupplier(null);
        itemService.updateItem(retrievedItem);

        Item received = itemService.fetchItemByItemId(retrievedItem.getItemId());
        Assertions.assertEquals(retrievedItem, received);

        retrievedOtherItem.setStatus("guestProvided");
        retrievedOtherItem.setSupplier("Person");
        itemService.updateItem(retrievedOtherItem);

        received = itemService.fetchItemByItemId(retrievedOtherItem.getItemId());
        Assertions.assertEquals(retrievedOtherItem, received);

        itemService.deleteItem(retrievedItem);
        itemService.deleteItem(retrievedOtherItem);
        potluckService.deletePotluck(retrievedPotlukk);
        potluckService.deletePotluck(retrievedOtherPotlukk);
        userRepo.delete(retrievedUser);
        userRepo.delete(retrievedOtherUser);
    }

    @Test
    void delete_item_test() {
        User user = new User(0, "Testing", "123455");
        User otherUser = new User(0, "Testign", "23456");

        User retrievedUser = userService.registerUser(user);
        User retrievedOtherUser = userService.registerUser(otherUser);

        Potlukk potlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedUser.getUserId());
        Potlukk otherPotlukk = new Potlukk(0, BigInteger.valueOf(System.currentTimeMillis()), retrievedOtherUser.getUserId());

        Potlukk retrievedPotlukk = potluckService.updatePotluck(potlukk);
        Potlukk retrievedOtherPotlukk = potluckService.updatePotluck(otherPotlukk);

        Item item = new Item(0, "guestProvided", "Sandwich", "Person", retrievedPotlukk.getPid());
        Item otherItem = new Item(0, "ownerWanted", "Sandwich", null, retrievedOtherPotlukk.getPid());

        Item retrievedItem = itemService.updateItem(item);
        Item retrievedOtherItem = itemService.updateItem(otherItem);

        itemService.deleteItem(retrievedItem);
        itemService.deleteItem(retrievedOtherItem);
        Assertions.assertEquals(0, itemService.fetchItemByItemId(item.getItemId()).getItemId());
        Assertions.assertEquals(0, itemService.fetchItemByItemId(otherItem.getItemId()).getItemId());
        potluckService.deletePotluck(retrievedPotlukk);
        potluckService.deletePotluck(retrievedOtherPotlukk);
        userRepo.delete(retrievedUser);
        userRepo.delete(retrievedOtherUser);
    }
}
