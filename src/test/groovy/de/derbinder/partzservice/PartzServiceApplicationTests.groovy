package de.derbinder.partzservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest()
class PartzServiceApplicationTests extends Specification {

    @Autowired
    ApplicationContext context

    def "Context loads"() {
        expect:
        context != null
    }
}
