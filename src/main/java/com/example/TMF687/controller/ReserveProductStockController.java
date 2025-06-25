package com.example.TMF687.controller;

import com.example.TMF687.model.ReserveProductStock;
import com.example.TMF687.model.ReserveProductStockItem;
import com.example.TMF687.service.ReserveProductStockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tmf-api/stock/v4/reserveProductStock")
public class ReserveProductStockController {

    private final ReserveProductStockService service;

    public ReserveProductStockController(ReserveProductStockService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listReserveProductStock(
            @RequestParam(required = false) String fields,
            @RequestParam(required = false) String state
    ) {
        List<ReserveProductStock> stocks = service.getAll(Optional.ofNullable(state));

        List<Map<String, Object>> projected = stocks.stream()
                .map(stock -> buildReserveProductStockResponse(stock, fields))
                .collect(Collectors.toList());

        return ResponseEntity.ok(projected);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getReserveProductStockById(
            @PathVariable String id,
            @RequestParam(required = false) String fields
    ) {
        try {
            ReserveProductStock stock = service.getById(id);
            return ResponseEntity.ok(buildReserveProductStockResponse(stock, fields));
        } catch (NoSuchElementException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Not Found");
            error.put("message", "ReserveProductStock with id " + id + " not found");
            error.put("status", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createReserveProductStock(@RequestBody ReserveProductStock reserveProductStock) {
        ReserveProductStock created = service.createReserveProductStock(reserveProductStock);

        Map<String, Object> response = buildReserveProductStockResponse(created, null);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private Map<String, Object> buildReserveProductStockResponse(ReserveProductStock stock, String fieldsParam) {
        Map<String, Object> fullMap = new LinkedHashMap<>();
        fullMap.put("id", stock.getId());
        fullMap.put("href", "http://localhost:8080/tmf-api/stock/v4/reserveProductStock/" + stock.getId());
        fullMap.put("reserveProductStockState", stock.getState() == null ? "accepted" : stock.getState());
        fullMap.put("@type", "ReserveProductStock");

        List<Map<String, Object>> items = (stock.getReserveProductStockItem() != null)
                ? stock.getReserveProductStockItem().stream()
                .map(this::buildReserveProductStockItemResponse)
                .collect(Collectors.toList())
                : new ArrayList<>();
        fullMap.put("reserveProductStockItem", items);

        // Optional field: creationDate
        fullMap.put("creationDate", stock.getCreationDate());

        // TMF fields filtering
        if (fieldsParam != null && !fieldsParam.isEmpty()) {
            List<String> fields = Arrays.stream(fieldsParam.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());

            if (fields.contains("id") && !fields.contains("href")) fields.add("href");
            if (fields.contains("href") && !fields.contains("id")) fields.add("id");

            Map<String, Object> filteredMap = new LinkedHashMap<>();
            for (String field : fields) {
                if (fullMap.containsKey(field)) {
                    filteredMap.put(field, fullMap.get(field));
                }
            }
            return filteredMap;
        }

        return fullMap;
    }


    private Map<String, Object> buildReserveProductStockItemResponse(ReserveProductStockItem item) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", item.getId());
        map.put("@type", "ReserveProductStockItem");
        map.put("quantityRequested", item.getQuantityRequested());
        map.put("quantityReserved", item.getQuantityReserved());

        // Optional fields
        if (item.getProductSpecification() != null) {
            map.put("productSpecification", item.getProductSpecification());
        }
        if (item.getProductCharacteristic() != null && !item.getProductCharacteristic().isEmpty()) {
            map.put("productCharacteristic", item.getProductCharacteristic());
        }
        return map;
    }
}
