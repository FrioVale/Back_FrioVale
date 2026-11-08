package com.pi.frioVale.controller;

import com.pi.frioVale.dto.ThingSpeakLogResponse;
import com.pi.frioVale.dto.ThingSpeakSyncResponse;
import com.pi.frioVale.service.ThingSpeakLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Tela de logs do IoT: consulta os registros já gravados e permite forçar uma nova coleta. */
@RestController
@RequestMapping("/api/thingspeak/logs")
@RequiredArgsConstructor
public class ThingSpeakLogController {

    private final ThingSpeakLogService thingSpeakLogService;

    @GetMapping
    public List<ThingSpeakLogResponse> list(@RequestParam(required = false) Long containerId) {
        return thingSpeakLogService.list(containerId);
    }

    /** Consulta os canais novamente e persiste as novas entradas. */
    @PostMapping("/sync")
    public ThingSpeakSyncResponse sync(
            @RequestParam(required = false) Long containerId,
            @RequestParam(defaultValue = "100") int results) {
        return new ThingSpeakSyncResponse(thingSpeakLogService.sync(containerId, results));
    }
}
