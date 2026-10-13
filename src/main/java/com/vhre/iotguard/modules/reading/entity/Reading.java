package com.vhre.iotguard.modules.reading.entity;

import com.vhre.base.core.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "readings")
@Getter
@Setter
public class Reading extends BaseEntity {

    @Column(name = "sensor_id", nullable = false, length = 50)
    private String sensorId;

    @Column(name = "temperature", nullable = false)
    private Double temperature;

    @Column(name = "humidity", nullable = false)
    private Double humidity;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;
}
