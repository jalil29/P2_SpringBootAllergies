package dev.springallergies.repos;


import dev.springallergies.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    List<User> findUserByUsername(String username);
}
