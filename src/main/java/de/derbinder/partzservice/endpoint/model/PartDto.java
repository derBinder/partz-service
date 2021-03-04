package de.derbinder.partzservice.endpoint.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class PartDto {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("weight")
    private Double weight;

    @JsonProperty("count")
    private Integer count;
}
