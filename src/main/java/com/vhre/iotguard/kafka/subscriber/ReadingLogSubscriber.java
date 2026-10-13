package com.vhre.iotguard.kafka.subscriber;

import com.vhre.iotguard.config.KafkaConfig;
import com.vhre.iotguard.kafka.events.ReadingEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReadingLogSubscriber {

    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = KafkaConfig.TOPIC_READINGS,
            groupId = "group-log"
    )
    public void handle(String message) {

        ReadingEvent event = objectMapper.readValue(message, ReadingEvent.class);

        log.info(
                "Received reading event: sensorId={}, temperature={}, humidity={}",
                event.sensorId(), event.temperature(), event.humidity()
        );
    }
}
