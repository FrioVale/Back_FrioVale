package com.pi.frioVale.service;

import com.pi.frioVale.dto.DriverStatsResponse;
import com.pi.frioVale.dto.TripMonitorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final TripService tripService;

    /** Consolida, por motorista, quantas viagens e qual o percentual de leituras dentro da faixa. */
    @Transactional(readOnly = true)
    public List<DriverStatsResponse> driverStats() {
        Map<Long, List<TripMonitorResponse>> byDriver = new LinkedHashMap<>();
        for (TripMonitorResponse trip : tripService.findAllMonitor()) {
            if (trip.driverId() != null) {
                byDriver.computeIfAbsent(trip.driverId(), id -> new ArrayList<>()).add(trip);
            }
        }

        return byDriver.values().stream().map(trips -> {
            TripMonitorResponse first = trips.get(0);
            long readings = trips.stream().mapToLong(TripMonitorResponse::readings).sum();
            long readingsInRange = trips.stream().mapToLong(TripMonitorResponse::readingsInRange).sum();
            Integer inRangePercent = readings == 0
                    ? null
                    : (int) Math.round(readingsInRange * 100.0 / readings);
            return new DriverStatsResponse(first.driverId(), first.driverName(), trips.size(), inRangePercent);
        }).toList();
    }
}
