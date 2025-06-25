package com.example.TMF687.repository;

import com.example.TMF687.model.AdjustProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdjustProductStockRepository extends JpaRepository<AdjustProductStock, String>, JpaSpecificationExecutor<AdjustProductStock> {
}
