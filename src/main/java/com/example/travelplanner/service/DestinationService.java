package com.example.travelplanner.service;

import com.example.travelplanner.repository.DestinationRepository;
import com.example.travelplanner.entity.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {
    @Autowired
    private DestinationRepository destinationRepository;
    public List<Destination> getAll() {
        return destinationRepository.findAll();
    }

    public Optional<Destination> getDestinationByID(Long id) {
        return destinationRepository.findById(id);
    }

    public Destination save(Destination destination){
        return destinationRepository.save(destination);
    }
}
