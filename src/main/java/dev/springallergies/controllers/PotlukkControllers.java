package dev.springallergies.controllers;


import dev.springallergies.entities.Potlukk;
import dev.springallergies.services.PotluckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin(origins="*", maxAge = 3600)
@Component
@Controller
public class PotlukkControllers {

    @Autowired
    private PotluckService potluckService;

    @GetMapping("/potlucks")
    @ResponseBody
    public List<Potlukk> retrievedPotlucks(){
        List<Potlukk> potlukks= this.potluckService.fetchPotlucks();
        return potlukks;
    }


}
