package com.pi.frioVale.dto;

import java.util.List;

/** Dados agregados da visão geral, sempre calculados a partir do banco. */
public record DashboardSummaryResponse(
        long activeTrips,
        long tripsInRange,
        Double idealMinTemp,
        Double idealMaxTemp,
        long activeAlerts,
        long activeDrivers,
        List<TripMonitorResponse> trips,
        List<AlertResponse> recentAlerts
) {
}
