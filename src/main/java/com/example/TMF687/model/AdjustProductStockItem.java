package com.example.TMF687.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class AdjustProductStockItem {

    @Id
    private String id = UUID.randomUUID().toString();

    @Embedded
    private Quantity adjustProductStockQuantity;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_stock_target_id")
    private ProductStock productStockTarget;

    private String type;

    public AdjustProductStockItem() {}

    // Getters and Setters...

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Quantity getAdjustProductStockQuantity() { return adjustProductStockQuantity; }
    public void setAdjustProductStockQuantity(Quantity adjustProductStockQuantity) { this.adjustProductStockQuantity = adjustProductStockQuantity; }

    public ProductStock getProductStockTarget() { return productStockTarget; }
    public void setProductStockTarget(ProductStock productStockTarget) { this.productStockTarget = productStockTarget; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
