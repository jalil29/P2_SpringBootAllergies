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
    List<Potlukk> findByCreatorid(int creatorid);

    //probably unnecessary since time is probably down to the second (or less)
    //if anything it would be the range of a day using two greater or lesser than start of date and end of date times
    //but any case probably unnecessary but might make decent bonus functionality later
    //List<Potlukk> findByTime(BigInteger time);
}
