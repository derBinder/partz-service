package de.derbinder.partzservice.service

import de.derbinder.partzservice.entity.Part
import de.derbinder.partzservice.repository.PartRepository
import spock.lang.Specification

class PartServiceTest extends Specification {

    private PartService partService
    private PartRepository partRepository = Mock()

    def setup() {
        partService = new PartService(partRepository)
    }

    def "GetAllParts"() {
        given:
        def expected = [new Part(), new Part()]
        partRepository.findAll() >> expected

        when:
        def actual = partService.getAllParts()

        then:
        actual == Optional.of(expected)
        actual.get().size() == 2
    }

    def "GetPartById"() {
        given:
        def uuid = UUID.randomUUID()
        def expected = Optional.of(new Part(id: uuid))
        partRepository.findById(uuid) >> expected

        when:
        def actual = partService.getPartById(uuid)

        then:
        actual == expected
        actual.get().id == expected.get().id
    }

    def "GetPartsByName"() {
        given:
        def expected = [new Part(name: "Lenker")]
        partRepository.findAllByNameContainsIgnoreCase("en") >> expected

        when:
        def actual = partService.getPartsByName("en")

        then:
        actual == Optional.of(expected)
        actual.get().size() == 1
    }

    def "CreatePart"() {
        given:
        def expected = new Part()
        partRepository.save(expected) >> expected

        when:
        def actual = partService.createPart(expected)

        then:
        actual == Optional.of(expected)
    }
}
