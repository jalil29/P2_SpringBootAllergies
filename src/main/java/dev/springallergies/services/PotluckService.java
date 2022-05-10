package dev.springallergies.services;

import dev.springallergies.entities.Potlukk;

import java.util.List;

public interface PotluckService {
    Potlukk fetchPotluckByPotID(int potluckId);

    List<Potlukk> fetchPotlucksByUserID(int userId);

    List<Potlukk> fetchPotlucks();

    Potlukk updatePotluck(Potlukk updatedPot);

    boolean deletePotluck(Potlukk deletePot);
}
