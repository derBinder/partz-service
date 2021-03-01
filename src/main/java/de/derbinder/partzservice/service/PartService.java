package de.derbinder.partzservice.service;

import de.derbinder.partzservice.entity.Part;
import de.derbinder.partzservice.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<List<Part>> getPartsById(Long id) {
        return Optional.of(partRepository.findAllById(id));
    }

    public Optional<List<Part>> getPartsByName(String name) {
        return Optional.of(partRepository.findAllByNameContains(name));
    }
}
