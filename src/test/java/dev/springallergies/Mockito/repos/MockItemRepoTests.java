package dev.springallergies.Mockito.repos;

import dev.springallergies.entities.Item;
import dev.springallergies.entities.Potlukk;
import dev.springallergies.entities.User;
import dev.springallergies.repos.ItemRepo;
import dev.springallergies.repos.PotlukkRepo;
import dev.springallergies.repos.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class MockItemRepoTests {

    @MockBean
    private ItemRepo itemRepo;
    @MockBean
    private UserRepo userRepo;
    @MockBean
    private PotlukkRepo potlukkRepo;

    //functions that Mockito checks for and then returns a pre-defined reference
    //non-stubbed stuff returns null
    public void init() {
        User retUser = new User(1, "test", "1234");
        when(userRepo.save(new User())).thenReturn(retUser);

        Potlukk retPotlukk = new Potlukk(1, BigInteger.valueOf(System.currentTimeMillis()), retUser.getUserId());
        when(potlukkRepo.save(new Potlukk())).thenReturn(retPotlukk);
        when(potlukkRepo.findById(retPotlukk.getPid())).thenReturn(Optional.of(retPotlukk));

        Item retItem = new Item(1, "ownerWanted", "Pasta salad", "Nobody", retPotlukk.getPid());
        when(itemRepo.save(new Item())).thenReturn(retItem);
        when(itemRepo.findById(retItem.getItemId())).thenReturn(Optional.of(retItem));
        retItem.setStatus("guestProvided");
        when(itemRepo.save(retItem)).thenReturn(retItem);
    }

    @Test
    void create_item_test() {
        init();

        User retrievedUser = userRepo.save(new User());

        Potlukk retrievedPotlukk = potlukkRepo.save(new Potlukk());

        Item receivedItem = itemRepo.save(new Item());

        Assertions.assertNotEquals(0, receivedItem.getItemId());

        itemRepo.delete(receivedItem);
        potlukkRepo.delete(retrievedPotlukk);
        userRepo.delete(retrievedUser);
    }

    @Test
    void get_items_test() {
        init();

        User retrievedUser = userRepo.save(new User());
        Potlukk retrievedPotlukk = potlukkRepo.save(new Potlukk());

        Item receivedItem = itemRepo.save(new Item());
        Item receivedOtherItem = itemRepo.save(new Item());

        when(itemRepo.findAll()).thenReturn(new ArrayList<Item>() {{
            add(receivedItem);
            add(receivedOtherItem);
        }});

        List<Item> retrieved = itemRepo.findAll();
        Assertions.assertTrue(retrieved.size() > 1);

        itemRepo.delete(receivedItem);
        itemRepo.delete(receivedOtherItem);
        potlukkRepo.delete(retrievedPotlukk);
        userRepo.delete(retrievedUser);
    }

    @Test
    void update_items_test() {
        init();
        User retrievedUser = userRepo.save(new User());

        Potlukk retrievedPotlukk = potlukkRepo.save(new Potlukk());

        Item receivedItem = itemRepo.save(new Item());

        receivedItem.setStatus("guestProvided");
        receivedItem = itemRepo.save(receivedItem);

        Item retrieved = itemRepo.findById(receivedItem.getItemId()).orElse(new Item(0, "", "", "", 0));
        Assertions.assertEquals(receivedItem.getStatus(), retrieved.getStatus());

        itemRepo.delete(receivedItem);
        potlukkRepo.delete(retrievedPotlukk);
        userRepo.delete(retrievedUser);
    }

    @Test
    void delete_items_test() {
        init();
        User retrievedUser = userRepo.save(new User());

        Potlukk retrievedPotlukk = potlukkRepo.save(new Potlukk());

        Item receivedItem = itemRepo.save(new Item());

        Assertions.assertTrue(itemRepo.findById(receivedItem.getItemId()).isPresent());

        itemRepo.delete(receivedItem);
        //Assertions.assertFalse(itemRepo.findById(receivedItem.getItemId()).isPresent());

        potlukkRepo.delete(retrievedPotlukk);
        userRepo.delete(retrievedUser);
    }

    @Test
    void delete_items_cascade() {
        init();
        User retrievedUser = userRepo.save(new User());

        Potlukk retrievedPotlukk = potlukkRepo.save(new Potlukk());

        Item receivedItem = itemRepo.save(new Item());

        userRepo.delete(retrievedUser);
        //Assertions.assertFalse(potlukkRepo.findById(retrievedPotlukk.getPid()).isPresent());
        //Assertions.assertFalse(itemRepo.findById(receivedItem.getItemId()).isPresent());
    }

    @Test
    void find_items_by_status() {
        init();
        User retrievedUser = userRepo.save(new User());

        Potlukk retrievedPotlukk = potlukkRepo.save(new Potlukk());

        Item receivedItem = itemRepo.save(new Item());
        Item receivedOtherItem = itemRepo.save(new Item());

        when(itemRepo.findByStatus("guestProvided")).thenReturn(new ArrayList<Item>() {{
            add(receivedItem);
            add(receivedOtherItem);
        }});

        List<Item> retrieved = itemRepo.findByStatus("guestProvided");
        Assertions.assertTrue(retrieved.size() > 0);

        itemRepo.delete(receivedItem);
        itemRepo.delete(receivedOtherItem);
        potlukkRepo.delete(retrievedPotlukk);
        userRepo.delete(retrievedUser);
    }

    @Test
    void find_items_by_supplier() {
        init();
        User retrievedUser = userRepo.save(new User());

        Potlukk retrievedPotlukk = potlukkRepo.save(new Potlukk());

        Item receivedItem = itemRepo.save(new Item());
        Item receivedOtherItem = itemRepo.save(new Item());

        when(itemRepo.findBySupplier("Nobody")).thenReturn(new ArrayList<Item>() {{
            add(receivedItem);
            add(receivedOtherItem);
        }});

        List<Item> retrieved = itemRepo.findBySupplier("Nobody");
        Assertions.assertTrue(retrieved.size() > 0);

        itemRepo.delete(receivedItem);
        itemRepo.delete(receivedOtherItem);
        potlukkRepo.delete(retrievedPotlukk);
        userRepo.delete(retrievedUser);
    }

    @Test
    void find_items_by_pid() {
        init();
        User retrievedUser = userRepo.save(new User());

        Potlukk retrievedPotlukk = potlukkRepo.save(new Potlukk());

        Item receivedItem = itemRepo.save(new Item());
        Item receivedOtherItem = itemRepo.save(new Item());

        when(itemRepo.findByPid(retrievedPotlukk.getPid())).thenReturn(new ArrayList<Item>() {{
            add(receivedItem);
            add(receivedOtherItem);
        }});

        List<Item> retrieved = itemRepo.findByPid(retrievedPotlukk.getPid());
        Assertions.assertTrue(retrieved.size() > 1);

        itemRepo.delete(receivedItem);
        itemRepo.delete(receivedOtherItem);
        potlukkRepo.delete(retrievedPotlukk);
        userRepo.delete(retrievedUser);
    }
}
