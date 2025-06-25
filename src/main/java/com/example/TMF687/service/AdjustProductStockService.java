package com.example.TMF687.service;

import com.example.TMF687.exception.ResourceNotFoundException;
import com.example.TMF687.model.AdjustProductStock;
import com.example.TMF687.model.AdjustProductStockItem;
import com.example.TMF687.repository.AdjustProductStockRepository;
import com.example.TMF687.repository.ProductStockRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class AdjustProductStockService {

    private final AdjustProductStockRepository repository;
    private final ProductStockRepository productStockRepository;

    public AdjustProductStockService(AdjustProductStockRepository repository, ProductStockRepository productStockRepository) {
        this.repository = repository;
        this.productStockRepository = productStockRepository;
    }

    public List<AdjustProductStock> getAll(Optional<Instant> requestedAfter) {
        if (requestedAfter.isPresent()) {
            return repository.findAll((root, query, cb) ->
                    cb.greaterThan(root.get("requestedAdjustProductStockDate"), requestedAfter.get()));
        }
        return repository.findAll();
    }

    public AdjustProductStock getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AdjustProductStock", "id", id));
    }

    public AdjustProductStock createAdjustProductStock(AdjustProductStock adjustProductStock) {
        if (adjustProductStock.getAdjustProductStockItem() == null || adjustProductStock.getAdjustProductStockItem().isEmpty()) {
            throw new IllegalArgumentException("adjustProductStockItem is mandatory and cannot be empty");
        }

        for (AdjustProductStockItem item : adjustProductStock.getAdjustProductStockItem()) {
            String targetId = item.getProductStockTarget() != null ? item.getProductStockTarget().getId() : null;
            if (targetId == null || !productStockRepository.existsById(targetId)) {
                throw new IllegalArgumentException("Invalid or missing productStockTarget id: " + targetId);
            }
            item.setProductStockTarget(productStockRepository.findById(targetId).get());
        }

        if (adjustProductStock.getCreationDate() == null) {
            adjustProductStock.setCreationDate(Instant.now());
        }

        return repository.save(adjustProductStock);
    }
}
