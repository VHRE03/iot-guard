package com.vhre.iotguard.modules.auditreading.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vhre.base.core.base.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Schema(description = "Data Transfer Object representing an AuditReading (audit trail of a consumed Reading)")
@JsonPropertyOrder({"id", "sensorId", "temperature", "humidity", "timestamp", "processedAt", "createdAt", "updatedAt", "deleted"})
public class AuditReadingDTO extends BaseDTO {

    @Schema(description = "Identificador del sensor que genero la lectura.", example = "SENSOR-001")
    @NotBlank
    @Size(max = 50)
    private String sensorId;

    @Schema(description = "Temperatura registrada por el sensor.", example = "22.5")
    @NotNull
    private Double temperature;

    @Schema(description = "Humedad relativa registrada por el sensor.", example = "60.2")
    @NotNull
    private Double humidity;

    @Schema(description = "Instante en el que el sensor genero la lectura.", example = "2026-10-12T10:15:30Z")
    @NotNull
    private Instant timestamp;

    @Schema(
            description = "Instante en el que el consumidor proceso la lectura (fijado automaticamente al crear).",
            example = "2026-10-12T10:15:35Z",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Instant processedAt;
}
