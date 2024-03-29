package com.example.travelplanner.service;

import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.entity.User;
import com.example.travelplanner.exceptions.UserNotFoundException;
import com.example.travelplanner.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User newUser){
        return userRepository.save(newUser);
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public List<Trip> getAllTripsOfUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        return user.getTrips();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    public void delete(Long tripId) {
        if (!userRepository.existsById(tripId)) {
            throw new EntityNotFoundException("user not found: " + tripId);
        }
        userRepository.deleteById(tripId);
    }
}
