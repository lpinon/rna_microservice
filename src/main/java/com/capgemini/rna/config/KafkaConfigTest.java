package com.capgemini.rna.config;

import com.capgemini.rna.models.requests.DecoderRequest;
import com.capgemini.rna.models.responses.DecoderSimpleResultResponse;
import com.capgemini.rna.services.KafkaService;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Profile("kafka-test")
public class KafkaConfigTest {

    @Bean
    @Primary
    public KafkaAdmin kafkaAdmin() {
        return Mockito.mock(KafkaAdmin.class);
    }

    @Bean
    @Primary
    public ProducerFactory<String, DecoderSimpleResultResponse> producerFactory() {
        return Mockito.mock(DefaultKafkaProducerFactory.class);
    }

    @Bean
    @Primary
    public KafkaTemplate<String, DecoderSimpleResultResponse> kafkaTemplate() {
        return Mockito.mock(KafkaTemplate.class);
    }

    @Bean
    @Primary
    public KafkaService kafkaService() {
        return Mockito.mock(KafkaService.class);
    }

}
