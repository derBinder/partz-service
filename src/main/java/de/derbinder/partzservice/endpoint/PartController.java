package de.derbinder.partzservice.endpoint;

import de.derbinder.partzservice.endpoint.model.PartDto;
import de.derbinder.partzservice.endpoint.model.PartsWithWeightDto;
import de.derbinder.partzservice.mapper.PartMapper;
import de.derbinder.partzservice.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
                .map(parts -> ResponseEntity.ok().body(partMapper.convertPartsToDtoWithWeight(parts)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @GetMapping("parts/{id}")
    public ResponseEntity<PartDto> getPartById(@PathVariable("id") UUID id) {
        return partService.getPartById(id)
                .map(part -> ResponseEntity.ok().body(partMapper.convertPartToDto(part)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    // TODO: Case insensitive
    @GetMapping("parts/search/{name}")
    public ResponseEntity<PartsWithWeightDto> getPartsByName(@PathVariable("name") String name) {
        return partService.getPartsByName(name)
                .map(parts -> ResponseEntity.ok().body(partMapper.convertPartsToDtoWithWeight(parts)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @PostMapping("parts/create")
    public ResponseEntity<PartDto> createPart(@RequestBody PartDto partDto) {
        return partService.createPart(partMapper.convertPartDtoToPart(partDto))
                .map(part -> ResponseEntity.ok().body(partMapper.convertPartToDto(part)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }
}
