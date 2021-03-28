package com.capgemini.rna.config;

import com.capgemini.rna.models.responses.DecoderSimpleResultResponse;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Profile("kafka-test")
public class KafkaConfigTest {

    @Bean
    public KafkaAdmin kafkaAdmin() {
        return Mockito.mock(KafkaAdmin.class);
    }

    @Bean
    public ProducerFactory<String, DecoderSimpleResultResponse> producerFactory() {
        return Mockito.mock(DefaultKafkaProducerFactory.class);
    }

    @Bean
    public KafkaTemplate<String, DecoderSimpleResultResponse> kafkaTemplate() {
        return Mockito.mock(KafkaTemplate.class);
    }

}
