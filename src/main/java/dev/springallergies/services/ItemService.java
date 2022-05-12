package dev.springallergies.services;

import dev.springallergies.entities.Item;
import dev.springallergies.entities.Potlukk;

import java.util.List;

public interface ItemService {
    Item fetchItemByItemId(int itemId);

    List<Item> fetchItems();

    List<Item> fetchItemsByPid(int userId);

    List<Item> fetchItemsByStatus(String status);

    List<Item> fetchItemsBySupplier(String supplier);

    Item updateItem(Item updatedItem);

    boolean deleteItem(Item deleteItem);
}
