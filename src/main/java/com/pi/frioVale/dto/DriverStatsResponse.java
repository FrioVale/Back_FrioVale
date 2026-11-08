package com.pi.frioVale.dto;

/** Estatísticas de um motorista calculadas a partir das viagens e telemetrias do banco. */
public record DriverStatsResponse(
        Long id,
        String name,
        long trips,
        Integer inRangePercent
) {
}
