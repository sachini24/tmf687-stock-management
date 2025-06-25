package com.example.TMF687.service;

import com.example.TMF687.exception.ResourceNotFoundException;
import com.example.TMF687.model.ProductStock;
import com.example.TMF687.repository.ProductStockRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStockService {

    private final ProductStockRepository repository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductStockService(ProductStockRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public ProductStock createProductStock(ProductStock productStock) {
        return repository.save(productStock);
    }

    public void deleteProductStock(String id) {
        ProductStock stock = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductStock", "id", id));
        repository.delete(stock);
    }

    public ProductStock getProductStockById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductStock", "id", id));
    }

    public List<ProductStock> getAllProductStock() {
        return repository.findAll();
    }

    public ProductStock patchProductStock(String id, JsonMergePatch patch) throws Exception {
        ProductStock existing = getProductStockById(id);
        JsonNode targetNode = objectMapper.valueToTree(existing);
        JsonNode patchedNode = patch.apply(targetNode);
        ProductStock patched = objectMapper.treeToValue(patchedNode, ProductStock.class);
        patched.setId(existing.getId());
        patched.setCreationDate(existing.getCreationDate());
        return repository.save(patched);
    }
}
