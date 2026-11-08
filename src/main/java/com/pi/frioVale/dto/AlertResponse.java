package com.pi.frioVale.dto;

import com.pi.frioVale.domain.Alert;
import com.pi.frioVale.domain.Fruit;
import com.pi.frioVale.domain.Telemetry;
import com.pi.frioVale.domain.Trip;

import java.time.LocalDateTime;

public record AlertResponse(
        Long id,
        Long tripId,
        String containerCode,
        String fruitName,
        Long telemetryId,
        Double temperature,
        Double minTemp,
        Double maxTemp,
        LocalDateTime telemetryTimestamp,
        String message,
        Boolean resolved,
        LocalDateTime createdAt
) {
    /** Deve ser chamado dentro de transação (acessa associações LAZY). */
    public static AlertResponse from(Alert a) {
        Telemetry telemetry = a.getTelemetry();
        Trip trip = telemetry.getTrip();
        Fruit fruit = trip.getFruit();
        return new AlertResponse(
                a.getId(),
                trip.getId(),
                trip.getContainer().getCode(),
                fruit.getName(),
                telemetry.getId(),
                telemetry.getTemperature(),
                fruit.getMinTemp(),
                fruit.getMaxTemp(),
                telemetry.getTimestamp(),
                a.getMessage(),
                a.getResolved(),
                a.getCreatedAt());
    }
}
