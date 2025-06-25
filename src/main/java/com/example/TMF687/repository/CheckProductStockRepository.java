package com.example.TMF687.repository;

import com.example.TMF687.model.CheckProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckProductStockRepository extends JpaRepository<CheckProductStock, Long>, JpaSpecificationExecutor<CheckProductStock> {
}
