package de.derbinder.partzservice.mapper;

import de.derbinder.partzservice.endpoint.model.PartDto;
import de.derbinder.partzservice.endpoint.model.PartsWithWeightDto;
import de.derbinder.partzservice.entity.Part;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

public class PartMapper {

    private final ModelMapper modelMapper;

    public PartMapper() {
        modelMapper = new ModelMapper();
    }

    public PartsWithWeightDto convertToDto(List<Part> parts) {
        PartsWithWeightDto partsWithWeightDto = new PartsWithWeightDto();
        PartDto[] partDtos = modelMapper.map(parts, PartDto[].class);

        partsWithWeightDto.setParts(Arrays.asList(partDtos));
        partsWithWeightDto.setCompleteWeight(calculateCompleteWeight(partDtos));

        return partsWithWeightDto;
    }

    private Double calculateCompleteWeight(PartDto[] parts) {
        return Arrays.stream(parts)
                .mapToDouble(partDto -> partDto.getWeight() * partDto.getCount())
                .sum();
    }
}
