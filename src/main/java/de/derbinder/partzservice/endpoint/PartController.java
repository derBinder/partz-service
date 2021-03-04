package de.derbinder.partzservice.endpoint;

import de.derbinder.partzservice.endpoint.model.PartDto;
import de.derbinder.partzservice.endpoint.model.PartsWithWeightDto;
import de.derbinder.partzservice.entity.Part;
import de.derbinder.partzservice.mapper.PartMapper;
import de.derbinder.partzservice.service.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("v1")
public class PartController {

    private final PartService partService;
    private final PartMapper partMapper;

    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;
        this.partMapper = new PartMapper();
    }

    @GetMapping("parts")
    public ResponseEntity<PartsWithWeightDto> getAllParts() {
        return partService.getAllParts()
                .map(parts -> ResponseEntity.ok().body(partMapper.convertToDto(parts)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @GetMapping("parts/{id}")
    public ResponseEntity<Part> getAllPartsById(@PathVariable("id") UUID id) {
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

    @PostMapping("parts/create")
    public ResponseEntity<Part> createPart(@RequestBody Part part) {
        return partService.createPart(part)
                .map(data -> ResponseEntity.ok().body(data))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }
}
