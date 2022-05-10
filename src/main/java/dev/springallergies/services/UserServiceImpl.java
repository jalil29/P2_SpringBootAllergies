package dev.springallergies.services;

import dev.springallergies.entities.User;
import dev.springallergies.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

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
        }else{
            throw new RuntimeException("No account with that user id");
        }
    }
}
