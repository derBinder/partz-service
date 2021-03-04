package de.derbinder.partzservice.service;

import de.derbinder.partzservice.entity.Part;
import de.derbinder.partzservice.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PartService {

    private final PartRepository partRepository;

    @Autowired
    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public Optional<List<Part>> getAllParts() {
        return Optional.of(partRepository.findAll());
    }

    public Optional<Part> getPartById(UUID id) {
        return partRepository.findById(id);
    }

    public Optional<List<Part>> getPartsByName(String name) {
        return Optional.of(partRepository.findAllByNameContainsIgnoreCase(name));
    }

    public Optional<Part> createPart(Part part) {
        return Optional.of(partRepository.save(part));
    }
}
