package com.capgemini.rna.services;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("parser-test")
@Configuration
public class ParserServiceTestConfiguration {
    @Bean
    @Primary
    public ParserService parserService() {
        return Mockito.mock(ParserService.class);
    }
}

