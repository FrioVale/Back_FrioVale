package com.pi.frioVale.controller;

import com.pi.frioVale.dto.AlertResponse;
import com.pi.frioVale.service.TelemetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final TelemetryService telemetryService;

    /** Todos os alertas, do mais recente para o mais antigo. */
    @GetMapping
    public List<AlertResponse> list() {
        return telemetryService.getAllAlerts();
    }
}
