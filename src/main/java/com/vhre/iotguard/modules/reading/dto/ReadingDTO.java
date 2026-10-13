package com.vhre.iotguard.modules.reading.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vhre.base.core.base.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Data Transfer Object representing a Reading (sensor measurement)")
@JsonPropertyOrder({"id", "sensorId", "temperature", "humidity", "createdAt", "updatedAt", "deleted"})
public class ReadingDTO extends BaseDTO {

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
}
