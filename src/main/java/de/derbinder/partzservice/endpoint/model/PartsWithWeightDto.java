package de.derbinder.partzservice.endpoint.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartsWithWeightDto {

    @JsonProperty("parts")
    private List<PartDto> parts;

    @JsonProperty("completeWeight")
    private Double completeWeight;
}
