package dev.springallergies.services;

import dev.springallergies.entities.User;
import dev.springallergies.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;


    @Override
    public User registerUser(User user) {
        return this.userRepo.save(user);
    }


    @Override
    public List<User> retrieveUsers() {
        return this.userRepo.findAll();
    }

    @Override
    public User getUserByIdNo(int userId) {
        Optional<User> possibleUser = this.userRepo.findById(userId);
        if(possibleUser.isPresent()){
            return possibleUser.get();
        }
        return new User();
    }
}
