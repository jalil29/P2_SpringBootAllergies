package dev.springallergies.controllers;


import dev.springallergies.entities.Potlukk;
import dev.springallergies.services.ItemService;
import dev.springallergies.services.PotluckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*", maxAge = 3600)
@Component
@Controller
public class PotlukkController {

    @Autowired
    private PotluckService potluckService;
    @Autowired
    private ItemService itemService;

    @GetMapping("/potlucks")
    @ResponseBody
    public List<Potlukk> retrievePotlucks(){
        List<Potlukk> potlukks= this.potluckService.fetchPotlucks();
        return potlukks;
    }

    @GetMapping("/potlucks/{creatorid}")
    @ResponseBody
    public List<Potlukk>  potluckksByCreator(@PathVariable int creatorid){
         List<Potlukk> requestedPotlucks= this.potluckService.fetchPotlucksByUserID(creatorid);
         return requestedPotlucks;
    }


    @PostMapping("/potlucks")
    @ResponseBody
    public Potlukk createPotlukk(@RequestBody Potlukk potlukk){
        return this.potluckService.updatePotluck(potlukk);
    }






}
