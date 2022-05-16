package dev.springallergies.controllers;

import dev.springallergies.entities.Item;
import dev.springallergies.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.List;

@Component
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/items")
    @ResponseBody
    public Item createItems(@RequestBody Item item){
        return this.itemService.updateItem(item);
    }


    @DeleteMapping("/items/{id}")
    @ResponseBody
    public void deleteItems(@PathVariable int id){
        Item receivedItem = itemService.fetchItemByItemId(id);
        if (receivedItem.getItemId() > 0) {
            if (this.itemService.deleteItem(receivedItem)) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/items")
    @ResponseBody
    public List<Item> retrievedItems(@RequestParam String status){
        if(status == null) {
            return this.itemService.fetchItems();
        }else if(status.equals("ownerWanted") || status.equals("guestProvided")){
            return this.itemService.fetchItemsByStatus(status);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/items/{pid}")
    @ResponseBody
    public List<Item> getByPId(@PathVariable int pid){
        return this.itemService.fetchItemsByPid(pid);
    }

}
