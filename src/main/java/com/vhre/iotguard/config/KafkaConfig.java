package com.vhre.iotguard.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Configuracion de Kafka para iot-guard.
 *
 * <p></p>
 */
@Configuration
public class KafkaConfig {

    public static final String TOPIC_READINGS = "readings";
    public static final String TOPIC_ALERTS = "alerts";
    public static final String TOPIC_SENSOR_STATE = "sensor-state";
    public static final String TOPIC_AVERAGES = "averages";

    @Bean
    public NewTopic readingsTopic() {
        return TopicBuilder.name(TOPIC_READINGS)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
