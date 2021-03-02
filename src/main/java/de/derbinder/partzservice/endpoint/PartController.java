package de.derbinder.partzservice.endpoint;

import de.derbinder.partzservice.entity.Part;
import de.derbinder.partzservice.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1")
public class PartController {

    private final PartService partService;

    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("parts")
    public ResponseEntity<List<Part>> getAllParts() {
        return partService.getAllParts()
                .map(parts -> ResponseEntity.ok().body(parts))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @GetMapping("parts/{id}")
    public ResponseEntity<Part> getAllPartsById(@PathVariable("id") Long id) {
        return partService.getPartById(id)
                .map(parts -> ResponseEntity.ok().body(parts))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @GetMapping("parts/search/{name}")
    public ResponseEntity<List<Part>> getPartsByName(@PathVariable("name") String name) {
        return partService.getPartsByName(name)
                .map(parts -> ResponseEntity.ok().body(parts))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @PostMapping("part")
    public ResponseEntity<Part> createPart(@RequestBody Part part) {
        return partService.createPart(part)
                .map(data -> ResponseEntity.ok().body(data))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }
}
