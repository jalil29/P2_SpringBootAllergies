package dev.springallergies.repos;

import dev.springallergies.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Component
@Repository
public interface ItemRepo extends JpaRepository<Item,Integer> {

    List<Item> findByStatus(String Status);
    List<Item> findBySupplier(String supplier);
    List<Item> findByPid(int pid);
}
