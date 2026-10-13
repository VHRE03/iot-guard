package com.vhre.iotguard.config;

import org.springframework.context.annotation.Configuration;

/**
 * Configuracion de Kafka para iot-guard.
 *
 * <p>De momento esta vacia a proposito: los beans de infraestructura (topics con
 * {@code NewTopic}, factorias de serializacion, etc.) los anadira el desarrollador
 * cuando implemente la logica de produccion/consumo en los paquetes
 * {@code kafka.producer} y {@code kafka.consumer}.</p>
 */
@Configuration
public class KafkaConfig {
}
