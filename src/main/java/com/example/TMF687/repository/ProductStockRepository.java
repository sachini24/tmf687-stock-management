package com.example.TMF687.repository;

import com.example.TMF687.model.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, String> {
}
