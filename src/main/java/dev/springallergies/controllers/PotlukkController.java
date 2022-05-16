package dev.springallergies.controllers;


import dev.springallergies.entities.Potlukk;
import dev.springallergies.services.ItemService;
import dev.springallergies.services.PotluckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@Controller
public class PotlukkController {

    @Autowired
    private PotluckService potluckService;

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

    @DeleteMapping("/potlucks/{pid}")
    public void deletePotluck(@PathVariable int pid){
        Potlukk requestedPotluck = potluckService.fetchPotluckByPotID(pid);
        if(requestedPotluck.getPid() > 0){
            if (potluckService.deletePotluck(requestedPotluck)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }



    }



}
