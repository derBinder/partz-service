package de.derbinder.partzservice.endpoint

import com.fasterxml.jackson.databind.ObjectMapper
import de.derbinder.partzservice.entity.Part
import de.derbinder.partzservice.service.PartService
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class PartControllerTest extends Specification {

    private ObjectMapper objectMapper;
    private MockMvc mvc
    private PartService partService = Mock()

    def setup() {
        objectMapper = new ObjectMapper()
        mvc = MockMvcBuilders.standaloneSetup(new PartController(partService)).build()
    }

    def "GetAllParts"() {
        given:
        def url = "/v1/parts"
        def expected = [];
        partService.getAllParts() >> Optional.of(expected);

        when:
        def actual = mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))

        then:
        actual.andExpect(status().isOk())
    }

    def "GetAllPartsById"() {
        given:
        def url = "/v1/parts/1"
        def expected = new Part();
        partService.getPartById(1) >> Optional.of(expected);

        when:
        def actual = mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))

        then:
        actual.andExpect(status().isOk())
    }

    def "GetPartsByName"() {
        given:
        def url = "/v1/parts/search/abc"
        def expected = [];
        partService.getPartsByName("abc") >> Optional.of(expected);

        when:
        def actual = mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))

        then:
        actual.andExpect(status().isOk())
    }

    def "CreatePart"() {
        given:
        def url = "/v1/part"
        def expected = new Part()
        partService.createPart(expected) >> Optional.of(expected)

        when:
        def actual = mvc.perform(post(url)
                .content(objectMapper.writeValueAsString(expected))
                .contentType(MediaType.APPLICATION_JSON)
        )

        then:
        actual.andExpect(status().isOk())
    }
}
