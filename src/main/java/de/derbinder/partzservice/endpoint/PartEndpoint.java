package de.derbinder.partzservice.endpoint;

import de.derbinder.partzservice.entity.Part;
import de.derbinder.partzservice.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("v1")
public class PartEndpoint {

    private final PartService partService;

    @Autowired
    public PartEndpoint(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("parts")
    public ResponseEntity<List<Part>> getAllParts() {
        return partService.getAllParts()
                .map(parts -> ResponseEntity.ok().body(parts))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @GetMapping("parts/{id}")
    public ResponseEntity<List<Part>> getAllParts(@PathVariable("id") Long id) {
        return partService.getPartsById(id)
                .map(parts -> ResponseEntity.ok().body(parts))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @GetMapping("parts/search/{name}")
    public ResponseEntity<List<Part>> getPartsByName(@PathVariable("name") String name) {
        return partService.getPartsByName(name)
                .map(parts -> ResponseEntity.ok().body(parts))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }
}
