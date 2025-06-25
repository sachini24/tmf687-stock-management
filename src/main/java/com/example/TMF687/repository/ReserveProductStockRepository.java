package com.example.TMF687.repository;

import com.example.TMF687.model.ReserveProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReserveProductStockRepository extends JpaRepository<ReserveProductStock, String>, JpaSpecificationExecutor<ReserveProductStock> {
}
