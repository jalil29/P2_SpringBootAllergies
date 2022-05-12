package dev.springallergies.services;

import dev.springallergies.entities.Item;
import dev.springallergies.repos.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    /**
     * @param itemId item identifier to be fetched
     * @return if found returns the associated item object otherwise return a default item object
     */
    @Override
    public Item fetchItemByItemId(int itemId) {
        Optional<Item> result = this.itemRepo.findById(itemId);
        if (result.isPresent())
            return result.get();
        return new Item();
    }

    /**
     * @return returns all items submitted
     */
    @Override
    public List<Item> fetchItems() {
        return this.itemRepo.findAll();
    }

    /**
     * @param pid potluck id to be used as a query
     * @return all items associated with potluck id
     */
    @Override
    public List<Item> fetchItemsByPid(int pid) {
        return this.itemRepo.findByPid(pid);
    }

    /**
     * @param status query by status type
     * @return returns all items with associated status
     */
    @Override
    public List<Item> fetchItemsByStatus(String status) {
        return this.itemRepo.findByStatus(status);
    }

    /**
     * @param supplier supplier name (typically first and last name) to query by
     * @return returns all items associated with supplier name
     */
    @Override
    public List<Item> fetchItemsBySupplier(String supplier) {
        return this.itemRepo.findBySupplier(supplier);
    }

    /**
     * @param updatedItem item object to be updated
     * @return updated item object
     */
    @Override
    public Item updateItem(Item updatedItem) {
        return this.itemRepo.save(updatedItem);
    }

    /**
     * @param deleteItem item object to be deleted
     * @return boolean based on error not being thrown if success
     */
    @Override
    public boolean deleteItem(Item deleteItem) {
        try {
            this.itemRepo.delete(deleteItem);
            return true;
        } catch (Exception e) {

        }
        return false;
    }
}
