package de.derbinder.partzservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .exposedHeaders(HttpHeaders.LOCATION, HttpHeaders.LINK, "Total-Items")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
                .allowedHeaders(HttpHeaders.ORIGIN, HttpHeaders.CONTENT_TYPE, HttpHeaders.CONTENT_LANGUAGE,
                        HttpHeaders.ACCEPT, HttpHeaders.ACCEPT_LANGUAGE, HttpHeaders.IF_MATCH,
                        HttpHeaders.IF_NONE_MATCH)
                .allowCredentials(true);
    }
}
