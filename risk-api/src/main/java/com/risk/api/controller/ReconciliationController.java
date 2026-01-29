package com.risk.api.controller;

import com.risk.api.dto.ReconciliationResult;
import com.risk.service.reconciliation.ReconciliationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/reconciliation")
@RequiredArgsConstructor
public class ReconciliationController {
    
    private final ReconciliationService reconciliationService;
    
    @PostMapping("/compare")
    public ReconciliationResult compare(@RequestBody Map<String, String> request) {
        String tableName = request.get("tableName");
        String idColumn = request.getOrDefault("idColumn", "id");
        return reconciliationService.compare(tableName, idColumn);
    }
}
