package dev.springallergies.services;

import dev.springallergies.entities.Potlukk;
import dev.springallergies.repos.PotlukkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class PotluckServiceImpl implements PotluckService {
    @Autowired
    private PotlukkRepo potlukkRepo;

    /**
     * @param potluckId identifier of a particular potluck
     * @return returns a potluck with the corresponding potluck ID or a default potlukk object
     */
    @Override
    public Potlukk fetchPotluckByPotID(int potluckId) {
        Optional<Potlukk> result = this.potlukkRepo.findById(potluckId);
        if (result.isPresent())
            return result.get();
        return new Potlukk();
    }

    /**
     * @param userId
     * @return returns all potlucks made by the user
     */
    @Override
    public List<Potlukk> fetchPotlucksByUserID(int userId) {
        return this.potlukkRepo.findByCreatorid(userId);
    }

    /**
     * @return returns all potlucks
     */
    @Override
    public List<Potlukk> fetchPotlucks() {
        return this.potlukkRepo.findAll();
    }

    /**
     * @param updatedPot potluck to be updated
     * @return updatedPotluck Object
     */
    @Override
    public Potlukk updatePotluck(Potlukk updatedPot) {
        return this.potlukkRepo.save(updatedPot);
    }

    /**
     * @param deletePot potluck to be removed
     * @return boolean based on error not being thrown if success
     */
    @Override
    public boolean deletePotluck(Potlukk deletePot) {
        try {
            this.potlukkRepo.delete(deletePot);
            return true;
        } catch (Exception e) {

        }
        return false;
    }
}
