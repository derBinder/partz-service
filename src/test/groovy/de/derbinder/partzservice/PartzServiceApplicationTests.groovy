package de.derbinder.partzservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Ignore
import spock.lang.Specification

@Ignore
@SpringBootTest(classes = [PartzServiceApplication])
class PartzServiceApplicationTests extends Specification {

    @Autowired
    ApplicationContext context

    def "Context loads"() {
        expect:
        context != null
    }
}
