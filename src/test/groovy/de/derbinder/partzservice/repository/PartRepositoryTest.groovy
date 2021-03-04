package de.derbinder.partzservice.repository

import de.derbinder.partzservice.entity.Part
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import spock.lang.Specification

@DataJpaTest()
class PartRepositoryTest extends Specification {

    @Autowired
    private PartRepository partRepository

    @Autowired
    private TestEntityManager entityManager

    def "FindById"() {
        given:
        def expected = entityManager.persistAndFlush(new Part())

        when:
        def actual = partRepository.findById(expected.id);

        then:
        actual == Optional.of(expected)
    }

    def "FindAllByNameContains"() {
        when:
        def actual = partRepository.findAllByNameContains("en")

        then:
        actual.size() == 2
    }

    def "DeleteById"() {
        given:
        def expected = entityManager.persistAndFlush(new Part())

        when:
        partRepository.deleteById(expected.id)
        def actual = partRepository.findById(expected.id);

        then:
        actual == Optional.empty()
    }
}
