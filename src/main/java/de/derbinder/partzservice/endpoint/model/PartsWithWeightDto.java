package de.derbinder.partzservice.endpoint.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PartsWithWeightDto {

    @JsonProperty("parts")
    private List<PartDto> parts;

    @JsonProperty("completeWeight")
    private Double completeWeight;
}
