package com.vhre.iotguard.kafka.publisher;

import com.vhre.iotguard.config.KafkaConfig;
import com.vhre.iotguard.kafka.events.ReadingEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component               // Spring: registra esta clase como bean.
@RequiredArgsConstructor // Lombok: genera constructor con los campos final (inyección por constructor).
@Slf4j                   // Lombok: crea el logger `log` (SLF4J).
public class ReadingPublisher {

    /**
     * Abstracción de alto nivel proporcionada por Spring para interactuar con Apache Kafka.
     * Facilita la publicación (producción) de mensajes o eventos hacia los tópicos de Kafka,
     * gestionando internamente la serialización de claves y valores (String, String),
     * el ciclo de vida del Producer y el manejo de excepciones subyacentes.
     */
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void publish(ReadingEvent event) {
        String payload = objectMapper.writeValueAsString(event);

        kafkaTemplate
                .send(KafkaConfig.TOPIC_READINGS, event.sensorId(), payload)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Failed to publish reading event: id={}", event.sensorId(), ex);
                    }
                });

        log.info("Published reading event: id={}", event.sensorId());
    }

}
