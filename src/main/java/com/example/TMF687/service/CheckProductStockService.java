package com.example.TMF687.service;

import com.example.TMF687.exception.ResourceNotFoundException;
import com.example.TMF687.model.CheckProductStock;
import com.example.TMF687.repository.CheckProductStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckProductStockService {

    private final CheckProductStockRepository repository;

    public CheckProductStockService(CheckProductStockRepository repository) {
        this.repository = repository;
    }

    public List<CheckProductStock> getAll(Optional<String> state) {
        if (state.isPresent()) {
            return repository.findAll((root, query, cb) ->
                    cb.equal(root.get("state"), state.get()));
        }
        return repository.findAll();
    }

    public CheckProductStock getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CheckProductStock", "id", id));
    }
}
