package com.example.TMF687.controller;

import com.example.TMF687.model.AdjustProductStock;
import com.example.TMF687.service.AdjustProductStockService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tmf-api/stock/v4/adjustProductStock")
public class AdjustProductStockController {

    private final AdjustProductStockService service;

    public AdjustProductStockController(AdjustProductStockService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listAdjustProductStock(
            @RequestParam(required = false) String fields,
            @RequestParam(name = "requestedAdjustProductStockDate.gt", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant requestedAfter
    ) {
        List<AdjustProductStock> stocks = service.getAll(Optional.ofNullable(requestedAfter));

        List<Map<String, Object>> projected = stocks.stream()
                .map(stock -> filterFields(stock, fields))
                .toList();

        return ResponseEntity.ok(projected);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getAdjustProductStockById(
            @PathVariable String id,
            @RequestParam(required = false) String fields
    ) {
        AdjustProductStock stock = service.getById(id);
        return ResponseEntity.ok(filterFields(stock, fields));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createAdjustProductStock(@RequestBody AdjustProductStock adjustProductStock) {
        AdjustProductStock created = service.createAdjustProductStock(adjustProductStock);
        Map<String, Object> response = new HashMap<>();
        response.put("id", created.getId());
        response.put("href", created.getHref());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private Map<String, Object> filterFields(AdjustProductStock stock, String fieldsParam) {
        Map<String, Object> fullMap = new HashMap<>();
        fullMap.put("id", stock.getId());
        fullMap.put("href", stock.getHref());
        fullMap.put("requestedAdjustProductStockDate", stock.getRequestedAdjustProductStockDate());
        fullMap.put("completedAdjustProductStockDate", stock.getCompletedAdjustProductStockDate());

        if (fieldsParam == null || fieldsParam.isEmpty()) {
            return fullMap;
        }

        List<String> fields = Arrays.stream(fieldsParam.split(","))
                .map(String::trim)
                .toList();

        return fullMap.entrySet().stream()
                .filter(entry -> fields.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
