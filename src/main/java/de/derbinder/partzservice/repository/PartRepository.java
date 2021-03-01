package de.derbinder.partzservice.repository;

import de.derbinder.partzservice.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    List<Part> findAllById(Long id);

    List<Part> findAllByNameContains(String name);

    void deleteById(Long id);
}
