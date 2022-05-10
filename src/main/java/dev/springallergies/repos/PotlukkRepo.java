package dev.springallergies.repos;

import dev.springallergies.entities.Potlukk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;


@Component
@Repository
public interface PotlukkRepo extends JpaRepository<Potlukk,Integer> {
    List<Potlukk> findPotlukkByCreatorid(int creatorid);
    List<Potlukk> findPotlukkByTime(BigInteger time);
}
