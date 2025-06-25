package com.example.TMF687.service;

import com.example.TMF687.exception.ResourceNotFoundException;
import com.example.TMF687.model.ReserveProductStock;
import com.example.TMF687.repository.ReserveProductStockRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ReserveProductStockService {

    private final ReserveProductStockRepository repository;

    public ReserveProductStockService(ReserveProductStockRepository repository) {
        this.repository = repository;
    }

    public List<ReserveProductStock> getAll(Optional<String> state) {
        if (state.isPresent()) {
            return repository.findAll((root, query, cb) ->
                    cb.equal(root.get("state"), state.get()));
        }
        return repository.findAll();
    }

    public ReserveProductStock getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ReserveProductStock", "id", id));
    }

    public ReserveProductStock createReserveProductStock(ReserveProductStock reserveProductStock) {
        if (reserveProductStock.getReserveProductStockItem() == null || reserveProductStock.getReserveProductStockItem().isEmpty()) {
            throw new IllegalArgumentException("reserveProductStockItem is mandatory and cannot be empty");
        }

        if (reserveProductStock.getCreationDate() == null) {
            reserveProductStock.setCreationDate(Instant.now());
        }

        if (reserveProductStock.getState() == null || reserveProductStock.getState().isBlank()) {
            reserveProductStock.setState("accepted"); // Default state
        }

        for (var item : reserveProductStock.getReserveProductStockItem()) {
            item.setReserveProductStock(reserveProductStock);
        }

        return repository.save(reserveProductStock);
    }

}
