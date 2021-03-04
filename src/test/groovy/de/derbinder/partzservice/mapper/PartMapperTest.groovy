package de.derbinder.partzservice.mapper

import de.derbinder.partzservice.endpoint.model.PartDto
import de.derbinder.partzservice.endpoint.model.PartsWithWeightDto
import de.derbinder.partzservice.entity.Part
import spock.lang.Specification

class PartMapperTest extends Specification {

    private PartMapper partMapper

    def setup() {
        partMapper = new PartMapper()
    }

    def "ConvertPartsToDtoWithWeight should build a correct DTO"() {
        given:
        Part part1 = new Part(id: UUID.randomUUID(), name: "Lenker", count: 1, weight: 2)
        Part part2 = new Part(id: UUID.randomUUID(), name: "Pedal", count: 2, weight: 2)
        List<Part> parts = [part1, part2]

        when:
        PartsWithWeightDto actual = partMapper.convertPartsToDtoWithWeight(parts)

        then:
        actual.parts.size() == 2
        actual.parts.get(0).name == "Lenker"
        actual.parts.get(1).name == "Pedal"
        actual.completeWeight == 6
    }

    def "ConvertPartsToDtoWithWeight should build a correct DTO when no parts are present"() {
        given:
        List<Part> parts = []

        when:
        PartsWithWeightDto actual = partMapper.convertPartsToDtoWithWeight(parts)

        then:
        actual.parts.size() == 0
    }

    def "ConvertPartToDto"() {
        given:
        def uuid = UUID.randomUUID()
        Part part = new Part(id: uuid)
        PartDto expected = new PartDto(id: uuid)

        when:
        PartDto actual = partMapper.convertPartToDto(part)

        then:
        actual.id == expected.id
    }

    def "ConvertPartDtoToPart"() {
        given:
        def uuid = UUID.randomUUID()
        PartDto partDto = new PartDto(id: uuid)
        Part expected = new Part(id: uuid)

        when:
        Part actual = partMapper.convertPartDtoToPart(partDto)

        then:
        actual.id == expected.id
    }
}
