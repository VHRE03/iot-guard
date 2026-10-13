package com.vhre.iotguard.kafka.events;

public record ReadingEvent(
        String sensorId,
        Double temperature,
        Double humidity
) {
}
