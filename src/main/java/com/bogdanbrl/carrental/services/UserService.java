package com.bogdanbrl.carrental.services;

import com.bogdanbrl.carrental.models.Rent;
import com.bogdanbrl.carrental.models.User;
import com.bogdanbrl.carrental.repository.RentRepository;
import com.bogdanbrl.carrental.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RentRepository rentRepository;

    public UserService(UserRepository userRepository, RentRepository rentRepository) {
        this.userRepository = userRepository;
        this.rentRepository = rentRepository;
    }

    public User getUserById(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword("");
        return user;
    }

    public List<User> getUsers(){
        List<User> users = userRepository.findAll();
        users.forEach(u -> u.setPassword(""));
        return Collections.unmodifiableList(users);
    }

    public void add(User user){
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if (userOptional.isPresent()){
            throw new RuntimeException("User with username: " + user.getUsername() + " already exist!");
        }
        userRepository.save(user);
    }

    public void edit(User user){
        try {
            User userDB = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User doesn't exist!"));
            userRepository.save(user);
        } catch (Exception e){
            System.out.println("Cannot update user details " + e.getMessage());
        }
    }

    public User getUserByUsername(String username){
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));
        return user;

    }

    public List<Rent> getUserHistory(Long userID) {
        User user = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User with id: " +userID+ " not found!"));
        List<Rent> rents = rentRepository.getUserHistory(user);
        rents.forEach(r -> r.getUser().setPassword(""));
        return rents;
    }

    public List<Rent> getUserHistory(User user) {
        List<Rent> rents = rentRepository.getUserHistory(user);
        rents.forEach(r -> r.getUser().setPassword(""));
        return rents;
    }
}
