package com.vhre.iotguard.kafka.events;

import java.time.LocalDateTime;

public record ReadingEvent(
        String sensorId,
        Double temperature,
        Double humidity,
        LocalDateTime createdAt
) {
}
