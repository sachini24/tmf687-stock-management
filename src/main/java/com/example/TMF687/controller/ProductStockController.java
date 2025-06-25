package com.example.TMF687.controller;

import com.example.TMF687.exception.ResourceNotFoundException;
import com.example.TMF687.model.ProductStock;
import com.example.TMF687.service.ProductStockService;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tmf-api/stock/v4/productStock")
public class ProductStockController {

    private final ProductStockService service;

    public ProductStockController(ProductStockService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductStock> getProductStockById(@PathVariable String id) {
        return ResponseEntity.ok(service.getProductStockById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductStock>> listProductStock() {
        return ResponseEntity.ok(service.getAllProductStock());
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createProductStock(@RequestBody ProductStock productStock) {
        ProductStock created = service.createProductStock(productStock);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("id", created.getIdAsString());
        response.put("href", created.getHref());
        response.put("productStockStatusType", created.getProductStockStatusType());
        response.put("productStockLevel", created.getProductStockLevel());
        response.put("stockedProduct", created.getStockedProduct());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductStock(@PathVariable String id) {
        service.deleteProductStock(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/{id}", consumes = { "application/merge-patch+json", "application/json" })
    public ResponseEntity<ProductStock> patchProductStock(
            @PathVariable String id,
            @RequestBody JsonMergePatch mergePatchDocument) {

        try {
            ProductStock updated = service.patchProductStock(id, mergePatchDocument);
            return ResponseEntity.ok(updated);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
