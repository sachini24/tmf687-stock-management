package com.example.TMF687.controller;

import com.example.TMF687.model.CheckProductStock;
import com.example.TMF687.service.AdjustProductStockService;
import com.example.TMF687.service.CheckProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tmf-api/stock/v4/checkProductStock")
public class CheckProductStockController {

    private final CheckProductStockService service;

    public CheckProductStockController(CheckProductStockService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listCheckProductStock(
            @RequestParam(required = false) String fields,
            @RequestParam(required = false) String state
    ) {
        List<CheckProductStock> stocks = service.getAll(Optional.ofNullable(state));

        List<Map<String, Object>> projected = stocks.stream()
                .map(stock -> filterFields(stock, fields))
                .toList();

        return ResponseEntity.ok(projected);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCheckProductStockById(
            @PathVariable Long id,
            @RequestParam(required = false) String fields
    ) {
        CheckProductStock stock = service.getById(id);
        return ResponseEntity.ok(filterFields(stock, fields));
    }

    private Map<String, Object> filterFields(CheckProductStock stock, String fieldsParam) {
        Map<String, Object> fullMap = new HashMap<>();
        fullMap.put("id", stock.getId());
        fullMap.put("href", stock.getHref());
        fullMap.put("creationDate", stock.getCreationDate());
        fullMap.put("state", stock.getState());

        List<Map<String, Object>> items = stock.getCheckProductStockItem().stream()
                .map(item -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", item.getId());
                    map.put("availabilityResult", item.getAvailabilityResult());
                    return map;
                })
                .collect(Collectors.toList());


        if (fieldsParam == null || fieldsParam.isEmpty()) {
            return fullMap;
        }

        // Filter fields - support nested field selection for first level only (simple version)
        List<String> fields = Arrays.stream(fieldsParam.split(","))
                .map(String::trim)
                .toList();

        return fullMap.entrySet().stream()
                .filter(entry -> fields.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
