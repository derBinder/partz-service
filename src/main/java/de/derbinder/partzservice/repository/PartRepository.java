package de.derbinder.partzservice.repository;

import de.derbinder.partzservice.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PartRepository extends JpaRepository<Part, UUID> {

    List<Part> findAllByNameContainsIgnoreCase(String name);

    void deleteById(UUID id);
}
